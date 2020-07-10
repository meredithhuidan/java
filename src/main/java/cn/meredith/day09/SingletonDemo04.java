package cn.meredith.day09;

/**
 * 单例创建方式
 * 双重检验锁方式
 *  (因为JVM本质重排序的原因，可能会初始化多次)
 *
 * @author Meredith
 * @date
 */
public class SingletonDemo04 {

    //volatile本身可以禁止重排序
    private static volatile SingletonDemo04 singletonDemo04;

    private static boolean flag=false;

    //单例防止反射漏洞攻击
    //在构造函数中，只能允许初始化一次即可
    private SingletonDemo04(){
        if (flag==false){
            flag=!flag;
        }else {
            throw new RuntimeException("单例模式被侵犯！");
        }
    }

    public static SingletonDemo04 getInstance(){
        if (singletonDemo04==null){
            synchronized (SingletonDemo04.class){
                if (singletonDemo04==null){
                    singletonDemo04=new SingletonDemo04();
                }
            }
        }
        return singletonDemo04;
    }

    public static void main(String[] args) {
        SingletonDemo04 s1=SingletonDemo04.getInstance();
        SingletonDemo04 s2=SingletonDemo04.getInstance();
        System.out.println(s1 ==s2);
    }
}
