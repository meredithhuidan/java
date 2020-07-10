package cn.meredith.day02;

/**
 * 用户线程是主线程创建的线程，非守护线程
 * 守护线程和主线程一起销毁
 * 设置守护线程.setDaemon()
 *
 * @author Meredith
 * @date
 */
public class DaemonThread {

    public static void main(String[] args) {

        Thread t1=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){

                    }
                    System.out.println("子线程...i:"+i);
                }
            }
        });
        //设置该线程为守护线程,和主线程一起销毁
        t1.setDaemon(true);
        t1.start();
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程...i:"+i);
        }
        System.out.println("主线程执行完毕.....");
    }
}
