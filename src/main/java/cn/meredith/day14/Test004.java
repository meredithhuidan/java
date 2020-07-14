package cn.meredith.day14;

/**
 * 虚拟机栈溢出
 * java.lang.StackOverflowError
 * 解决办法:设置线程最大调用深度
 * -Xss5m 设置最大调用深度
 *
 * @author Meredith
 * @date
 */
public class Test004 {

    private static int count;

    public static void count() {
        try {
            count++;
            count();
        } catch (Throwable e) {
            System.out.println("最大深度:" + count);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        count();
    }
}
