package cn.meredith.day09;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式 创建方式
 * 饿汉式
 * 类初始化的时候，就创建对象，天生线程安全，调用效率较高，如果在不使用对象的时候，会浪费内存
 *
 * @author Meredith
 * @date
 */
public class SingletonDemo01{

    private static final SingletonDemo01  singletonDemo01=new SingletonDemo01();    //垃圾回收机制不会回收 存在永久区
    private static boolean FLAG=false;

    //1、将构造函数私有化
    //单例防止反射漏洞攻击
    //在构造函数中，只能允许初始化一次即可
    //在构造函数里设置一个标识
    private SingletonDemo01(){
        synchronized (SingletonDemo01.class){
            System.out.println("flag:"+FLAG);
            if (FLAG==false){
                //将FLAG改为true
                FLAG=!FLAG;
            }else {
                System.out.println("该对象是单例的，不能够重复创建!");
                throw new RuntimeException("单例模式被侵犯！");
            }
            System.out.println("SingletonDemo01对象"+FLAG);
        }
    }

    //会有线程安全问题吗？为什么不会产生线程安全问题？static
    public static SingletonDemo01 getInstance(){
        System.out.println("getInstance()");
        return singletonDemo01;
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        SingletonDemo01  u1=SingletonDemo01.getInstance();
        SingletonDemo01  u2=SingletonDemo01.getInstance();
        System.out.println(u1==u2);

        //使用反射初始化对象
//        Class forName=Class.forName("cn.meredith.day09.SingletonDemo01");
//        Constructor declaredConstructor=forName.getDeclaredConstructor(null);
//        declaredConstructor.setAccessible(true);
//        Object singletonDemo01=declaredConstructor.newInstance();

    }

}
