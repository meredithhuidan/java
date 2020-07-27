package cn.meredith.day22.mybatis.aop;

import cn.meredith.day22.mybatis.annotation.ExtInsert;
import cn.meredith.day22.mybatis.annotation.ExtParam;
import cn.meredith.day22.mybatis.annotation.ExtSelect;
import cn.meredith.day22.mybatis.util.JDBCUtils;
import cn.meredith.day22.mybatis.util.SQLUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用反射动态代理技术 拦截接口方法
 *
 * @author Meredith
 * @date
 */
public class MyInvocationHandlerMybatis implements InvocationHandler {

    private Object object;

    public MyInvocationHandlerMybatis(Object object) {
        this.object = object;
    }

    /**
     * @param proxy  代理对象
     * @param method 拦截方法
     * @param args   方法上的参数值
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("使用动态代理技术拦截方法");
        //@ExtInsert封装的过程
        //1、判断方法上是否存在@ExtInsert
        ExtInsert extInsert = method.getDeclaredAnnotation(ExtInsert.class);
        if (extInsert != null) {
            return extInsert(extInsert,proxy, method, args);
            }
        //查询的思路
        //1、判断方法上是否存在注解
        ExtSelect extSelect=method.getDeclaredAnnotation(ExtSelect.class);
        if (extSelect!=null) {
            //2、获取注解上查询的sql语句
            String selectSql=extSelect.value();
            //3、获取方法上参数，绑定在一起
            ConcurrentHashMap paramsMap = paramMap(proxy, method, args);
            //4、参数替换？传递方式 存放sql执行的参数---参数绑定
            List<String> sqlSelectParameter=SQLUtils.sqlSelectParameter(selectSql);
            //5、传递参数
            List<Object> sqlParams=new ArrayList<>();
            for (String paramName:sqlSelectParameter) {
                Object paramValue=paramsMap.get(paramName);
                sqlParams.add(paramValue);
            }
            //6、将sql语句替换成？
            String newSql=SQLUtils.parameQuestion(selectSql,sqlSelectParameter);
            System.out.println("newSql:"+newSql+",sqlParams:"+sqlParams.toString());
            //7、调用jdbc代码底层执行sql语句
            ResultSet res =JDBCUtils.query(newSql,sqlParams);
            //8、使用java反射机制实例对象————获取方法返回的类型，进行实例化
            //思路：1）使用反射机制获取方法类型
            //      2）判断是否有结果集，如果有，再进行初始化
            //      3）使用反射机制，给对象赋值

            //判断是否存在值
//            if (!res.next()){
//                return null;
//            }
//            //下标往上移动一位
//            res.previous();
            //1）使用反射机制获取方法类型
            Class returnType=method.getReturnType();
            Object object=returnType.newInstance();
            while (res.next()){
                //方法二：
                //获取当前所有的属性
                Field[] declaredFields=returnType.getDeclaredFields();
                for (Field field :
                        declaredFields) {
                    String filedName=field.getName();
                    Object fieldValue=res.getObject(filedName);
                    field.setAccessible(true);
                    field.set(object,fieldValue);
                }
                //方法一：
//                for (String paramName:sqlSelectParameter) {
//                    //获取参数值
//                    Object resultValue=res.getObject(paramName);
//                    //使用Java的反射值赋值
//                    Field field =returnType.getDeclaredField(paramName);
//                    //私有方法允许访问
//                    field.setAccessible(true);
//                    field.set(object,resultValue);
//                }
            }
            return object;
        }
        return null;
    }

    /**
     * 插入insert
     * @param extInsert
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    private Object extInsert(ExtInsert extInsert,Object proxy, Method method, Object[] args){
        //2、获取sql语句，获取注解insert语句
        String insertSql = extInsert.value();
        System.out.println(insertSql);
        //3、获取方法的参数和sql参数进行匹配,获取方法上的参数
        //定义一个Map集合，key为@ExtParam value,value为参数值
        ConcurrentHashMap paramsMap = paramMap(proxy, method, args);
        //存放sql执行的参数---参数绑定
        String[] sqlInsertParameter = SQLUtils.sqlInsertParameter(insertSql);
        List<Object> sqlParams = sqlParams(sqlInsertParameter, paramsMap);
        //4、根据参数替换参数变为？ ————变成jdbc能执行的语句
        String newSql = SQLUtils.parameQuestion(insertSql, sqlInsertParameter);
        System.out.println("newSql:" + newSql);
        //5、调用jdbc底层代码执行语句
        return JDBCUtils.insert(newSql, false, sqlParams);

    }

    /**
     * 获取方法上的参数
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     */
    private ConcurrentHashMap paramMap(Object proxy, Method method, Object[] args) {
        ConcurrentHashMap paramsMap = new ConcurrentHashMap<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            ExtParam extParam = parameter.getDeclaredAnnotation(ExtParam.class);
            if (extParam != null) {
                //参数名称
                String paramName = extParam.value();
                Object paramValue = args[i];
                System.out.println("paramName:" + paramName + ",paramValue:" + paramValue);
                paramsMap.put(paramName, paramValue);
            }
        }
        return paramsMap;
    }

    /**
     * @param sqlInsertParameter
     * @param paramsMap
     * @return
     */
    private List sqlParams(String[] sqlInsertParameter, ConcurrentHashMap paramsMap) {
        List<Object> sqlParams = new ArrayList<>();
        for (String paramName : sqlInsertParameter) {
            Object paramValue = paramsMap.get(paramName);
            sqlParams.add(paramValue);
        }
        return sqlParams;
    }

}
