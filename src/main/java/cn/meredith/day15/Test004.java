package cn.meredith.day15;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.Method;

/**
 * 使用javassit动态修改class文件
 *
 * @author Meredith
 * @date
 */
public class Test004 {

    public static void main(String[] args) {

        try {
            ClassPool pool=ClassPool.getDefault();
            CtClass userClass =pool.get("cn.meredith.day15.classes.User");
            CtMethod method=new CtMethod(CtClass.voidType,"sum",
                    new CtClass[]{CtClass.intType,CtClass.intType},userClass);
            method.setBody("{System.out.println(\"sum:\"+($1+$2));}");
            //添加方法
            userClass.addMethod(method);

            userClass.writeFile("E://Java_project//WrokSpace//thread_demo//src//main//java//cn//meredith//day15");

            //动态执行方法 使用反射技术
            Class aClass=userClass.toClass();
            //导致java.lang.InstantiationException异常
            //多半是由于通过反射在实例化的时候，对应的类里面覆盖了无参构造而导致无法实例化
            //所以建议在创建类的时候，最好还是保留（显式的写出来）无参构造
            Object newInstance=aClass.newInstance();
            Method sunMethod=aClass.getDeclaredMethod("sum",int.class,int.class);

            //使用javassit实现动态代理
            System.out.println("开启事务");
            sunMethod.invoke(newInstance,1,5);
            System.out.println("结束事务");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
