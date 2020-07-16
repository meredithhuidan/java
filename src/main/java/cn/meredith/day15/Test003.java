package cn.meredith.day15;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用反射技术执行某方法
 *
 * @author Meredith
 * @date
 */
public class Test003 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        sum(1,2);   //一般可视化执行
        //使用反射技术执行某方法
        Class forName=Class.forName("cn.meredith.day15.Test003");
        Object newInstance=forName.newInstance();
        Method method =forName.getDeclaredMethod("sum", int.class, int.class);
        method.invoke(newInstance,1,5);     //动态代理
    }

    static public void sum(int a,int b){
        System.out.println("sum:"+(a+b));
    }
}
