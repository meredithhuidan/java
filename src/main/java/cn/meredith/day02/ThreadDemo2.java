package cn.meredith.day02;

/**
 * @desc 实现runnable接口，重写run方法
 * @author Meredith
 * @date
 */
class CreateRunnable implements Runnable{

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程...i:"+i);
        }
    }
}

public class ThreadDemo2 {
    public static void main(String[] args) {

        System.out.println("main...主线程开始");
        //创建线程
        CreateRunnable createRunnable=new CreateRunnable();
        Thread thread=new Thread(createRunnable);
        //启动线程
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程...i:"+i);
        }
        System.out.println("main...主线程结束");
    }
}
