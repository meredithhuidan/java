package cn.meredith.day09;

/**
 * 单例模式 创建方式
 * 懒汉式
 * 类初始化的时候，不会创建该对象，真正需要的时候才会加载（创建）
 * 天生线程不安全，需要解决线程安全问题，所以效率比较低
 *
 * @author Meredith
 * @date
 */
public class SingletonDemo02 {

    private static SingletonDemo02 singletonDemo02;

    private SingletonDemo02(){

    }

    //线程安全问题 效率低
    public static synchronized SingletonDemo02 getInstance(){
        if (singletonDemo02==null){
            //多线程 可能创建多个对象 用synchronized
            singletonDemo02=new SingletonDemo02();
        }
        return singletonDemo02;
    }

    public static void main(String[] args) {
        SingletonDemo02 s1=SingletonDemo02.getInstance();
        SingletonDemo02 s2=SingletonDemo02.getInstance();
        System.out.println(s1 ==s2);
    }
}
