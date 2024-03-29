package cn.meredith.day20.springmvc.servlet;

import cn.meredith.day19.util.ClassUtil;
import cn.meredith.day20.springmvc.annotation.ExtController;
import cn.meredith.day20.springmvc.annotation.ExtRequestMapping;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义前端控制器
 * 手写springmvc 原理分析
 *  * 1、创建一个前端控制器 ExtDispatcherServlet,拦截所有请求（springmvc 基于servlet实现）
 *  * 2、初始化操作：重写servlet init()方法
 *  *      2.1、将扫包范围所有的类，注入到springmvc容器里面，存放在Map集合中，key默认类名小写，value为创建好的对象
 *  *      2.2、将URL映射和方法进行关联。handlerMapping
 *  *              2.2.1、解析类上是否有注解，使用java反射机制循环遍历方法，判断方法上是否存在注解，进行封装url和方法对应存入集合中
 *  * 3、处理请求 重写Get或者Post方法
 *  *      3.1、获取请求URL,去urlBeans集合中获取实例对象，获取成功实例对象后，调用urlMethods集合获取方法名称，使用反射机制执行
 *  *
 *
 * @author Meredith
 * @date
 */
public class ExtDispatcherServlet extends HttpServlet {

    //springmvc容器对象 key:类名id value:对象
    private ConcurrentHashMap<String  ,Object > springmvcBeans=new ConcurrentHashMap();
    //key:请求地址 value:value类
    private ConcurrentHashMap<String ,Object > urlBeans=new ConcurrentHashMap();
    //key:请求地址 value:value方法名称
    private ConcurrentHashMap<String  ,String  > urlMethods=new ConcurrentHashMap();


    @Override
    public void init() throws ServletException {
        //1、获取当前包下所有的类
        List<Class<?>> classes =ClassUtil.getClasses("cn.meredith.day20.springmvc.controller");
        //2、判断类上是否有加controller注解
        try {
            findClassMVCAnnotation(classes);
        }catch (Exception e){
            e.printStackTrace();
        }
        //3、将URL映射和方法进行关联。handlerMapping
        handlerMapping();
    }

    /**
     * 将扫包范围所有的类，注入到springmvc容器里面，存放在Map集合中，key默认类名小写，value为创建好的对象
     * @param classes
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public void findClassMVCAnnotation(List<Class<?>> classes) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        for (Class classInfo :classes) {
            //判断类上是否有加上注解
            ExtController extController= (ExtController) classInfo.getDeclaredAnnotation(ExtController.class);
            if (extController!=null){
                //默认类名小写
                String beanId=ClassUtil.toLowerCaseFirstOne(classInfo.getSimpleName());
                //实例化对象
                Object object= ClassUtil.newInstance(classInfo);
                springmvcBeans.put(beanId,object);
            }
        }
    }

    /**
     * 将URL映射和方法进行关联。
     */
    public void handlerMapping(){
        //1、获取springmvc bean容器对象
        for (Map.Entry mvcBean:springmvcBeans.entrySet()){
            //2、遍历springmvc bean容器 判断类上是否有url映射注解
            //获取bean对象
            Object object=mvcBean.getValue();
            //3、判断类上是否有加url映射注解
            Class classInfo=object.getClass();
            ExtRequestMapping declaredAnnotation= (ExtRequestMapping) classInfo.getDeclaredAnnotation(ExtRequestMapping.class);
            String baseUrl="";
            if (declaredAnnotation!=null){
                //获取类上的url映射地址
                baseUrl=declaredAnnotation.value();
            }
            //4、判断方法上是否有加url映射地址
            Method[] methods =classInfo.getDeclaredMethods();
            for (Method method :methods) {
                //判断方法上是否有加url映射注解
                ExtRequestMapping methodExtRequestMapping=method.getDeclaredAnnotation(ExtRequestMapping.class);
                if (methodExtRequestMapping!=null){
                    String methodUrl=baseUrl+methodExtRequestMapping.value();
                    //springmvc 容器对象 key:请求地址 value:类
                    urlBeans.put(methodUrl,object);
                    //springmvc 容器对象 key:请求地址 value:方法名称
                    urlMethods.put(methodUrl,method.getName());
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //处理请求
        //1、获取url地址
        String requestURI=req.getRequestURI();
        if (StringUtils.isEmpty(requestURI)){
            return;
        }
        //2、从Map集合中获取控制对象
        Object object=urlBeans.get(requestURI);
        if (object== null){
            resp.getWriter().println("not found 404 url");
            return;
        }
        //3、使用url地址获取方法
        String  methodName=urlMethods.get(requestURI);
        if (StringUtils.isEmpty(methodName)){
            resp.getWriter().println("not found method");
            return;
        }
        //4、使用Java的反射机制调用方法
        String  resultPage= (String) methodInvoke(object,methodName);
        resp.getWriter().println(resultPage);
        //5、使用java的反射机制获取方法返回结果
        //6、调用视图转换器 渲染给页面展示
        extResourceViewResolver(resultPage,req,resp);

    }

    private void extResourceViewResolver(String pageName,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //根路径
        String prefix="/";
        String suffix=".jsp";
        request.getRequestDispatcher(prefix+pageName+suffix).forward(request,response);
    }

    private Object methodInvoke(Object object,String methodName){
        try {
            Class classInfo=object.getClass();
            Method method=classInfo.getMethod((methodName));
            Object result=method.invoke(object);
            return result;
        }catch (Exception e){
            return null;
        }
    }
}
