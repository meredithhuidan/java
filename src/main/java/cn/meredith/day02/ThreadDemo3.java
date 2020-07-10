package cn.meredith.day02;

/**
 * 类里面嵌套类。即内部类
 * 使用匿名内部类创建线程
 *
 * @author Meredith
 * @date
 */
public class ThreadDemo3 {

    public static void main(String[] args) {

        System.out.println("main...主线程开始...");
        Thread t1=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("子线程...i:"+i);
                }
            }
        });
        t1.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程...i:"+i);
        }
        System.out.println("main...主线程结束...");
    }
}
