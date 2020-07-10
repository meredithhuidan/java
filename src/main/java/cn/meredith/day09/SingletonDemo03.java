package cn.meredith.day09;

/**
 * 单例模式 创建方式
 * 静态内部类
 */
public class SingletonDemo03 {

    private SingletonDemo03() {
        System.out.println("初始化..");
    }

    public static class SingletonClassInstance {
        private static final SingletonDemo03 singletonDemo03 = new SingletonDemo03();
    }

    // 方法没有同步
    public static SingletonDemo03 getInstance() {
        System.out.println("getInstance");
        return SingletonClassInstance.singletonDemo03;
    }

    public static void main(String[] args) {
        SingletonDemo03 s1 = SingletonDemo03.getInstance();
        SingletonDemo03 s2 = SingletonDemo03.getInstance();
        System.out.println(s1 == s2);
    }
}
