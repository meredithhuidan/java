package cn.meredith.day19.spring.ext;

import cn.meredith.day19.spring.extannotation.ExtResource;
import cn.meredith.day19.spring.extannotation.ExtService;
import cn.meredith.day19.util.ClassUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 手写springIOC 注解版本
 * 依赖注入原理
 *
 * @author Meredith
 * @date
 */
public class ExtClassPathXmlApplicationContext1 {

    //扫包的范围
    private String packageName;

    //ConcurrentHashMap 线程安全
    //springbean容器
    private static ConcurrentHashMap<String,Object> beans=null;

    public ExtClassPathXmlApplicationContext1(String packageName) throws Exception {
        this.packageName=packageName;
        beans=new ConcurrentHashMap<>();
        initBean();
        initEntryField();
    }

    /**
     * 初始化属性
     * @throws Exception
     */
    public void initEntryField() throws Exception {
        //1、遍历所有的bean容器对象
        for (Map.Entry<String ,Object> entry:beans.entrySet()) {
            //2、判断属性上是否加注解
            Object bean=entry.getValue();
            attriAssign(bean);
        }
    }

    public Object getBean(String beanId) throws Exception {
        if (StringUtils.isEmpty(beanId)){
            throw new Exception("beanId不能为空");
        }
        //1、从spring容器获取bean
        Object object=beans.get(beanId);
//        if (object==null){
//            throw new Exception("class not found");
//        }
        //2、使用反射机制初始化对象
        return object;
    }

    /**
     * 初始化对象
     *
     * @param classInfo
     * @return
     */
    public Object newInstance(Class<?> classInfo) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return classInfo.newInstance();
    }

    //初始化对象
    public void initBean() throws Exception {
        //1、使用java反射机制扫包，获取当前包下所有的类
        List<Class<?>> classes =ClassUtil.getClasses(packageName);
        //2、判断类上是否存在注入bean注解
        ConcurrentHashMap<String ,Object> classExisAnnotation=findClassExisAnnotation(classes);
        if (classExisAnnotation==null){
            throw  new Exception("该包下没有任何类加注解");
        }
        //3、使用Java的反射机制进行初始化
    }

    //判断类上面是否存在注入bean的注解
    public ConcurrentHashMap<String, Object> findClassExisAnnotation(List<Class<?>> classes) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        for (Class classInfo:classes){
            //判断类上是否有注解
            ExtService annotation= (ExtService) classInfo.getAnnotation(ExtService.class);
            if (annotation!=null){
                //beanId 默认类名小写
                //获取当前类名
                String className=classInfo.getSimpleName();
                //将当前类名变为小写
                String beanId=toLowerCaseFirstOne(className);
                Object newInstance=newInstance(classInfo);
                beans.put(beanId,newInstance);
                continue;
            }
        }
        return beans;
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s){
        if (Character.isLowerCase(s.charAt(0))){
            return s;
        }else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    //依赖注入注解原理
    public void attriAssign(Object object) throws Exception {
        Class classInfo=object.getClass();
        //1、使用反射机制，获取当前类的所有属性
        Field[] declaredFields =classInfo.getDeclaredFields();
        //2、判断当前类属性是否存在注解
        for (Field field:declaredFields) {
            ExtResource extResource=field.getAnnotation(ExtResource.class);
            if (extResource!=null){
                //获取属性名称
                String beanId=field.getName();
                Object bean=getBean(beanId);
                if (bean!=null){
                    //3、默认使用属性名称，查找bean容器对象
                    //第一个参数 当前对象；第二个参数 给属性赋值
                    field.setAccessible(true);  //允许访问私有属性
                    field.set(object,bean);
                }
            }
        }
    }

}
