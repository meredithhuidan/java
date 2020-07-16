package cn.meredith.day16;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 热部署程序入口
 * 直接将2.0版本覆盖1.0版本，能不能读到最新的？不能
 * Java的class文件生成在target目录下
 */
public class Hotswap {
//    public static void main(String[] args) throws InterruptedException {
//
//        System.out.println("开始加载user1.0版本");
//        User user1=new User();
//        user1.add();
//
//        Thread.sleep(10*1000);
//        System.out.println("开始加载user2.0版本");
//        //将user2.0版本class文件覆盖user1.0版本class文件
//        User user2=new User();
//        user2.add();
//    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, InterruptedException {
        System.out.println("开始加载user1.0版本");
        loadUser();
        System.gc();    //user对象回收
        //需要被修改的class文件
        File file1=new File("");
        //之前的class文件
        File file2=new File("");
        boolean isDelete=file2.delete();
        if (!isDelete){
            System.out.println("热部署失败，无法删除文件...");
            return;
        }
        file1.renameTo(file2);   //移动到file2目录
        Thread.sleep(15*1000);
        System.out.println("开始加载user2.0版本");
        loadUser();
    }

    public static void loadUser() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader();
        //使用类加载器读取信息
        Class findClass = myClassLoader.findClass("cn.meredith.day16.User");
        //使用反射机制初始化对象
        Object object = findClass.newInstance();
        //使用反射机制调用方法
        Method method = findClass.getMethod("add");     //反射
        method.invoke(object);
        System.out.println(object.getClass());
        System.out.println(object.getClass().getClassLoader());

    }
}
