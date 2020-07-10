package cn.meredith.day05;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 线程池
 * newScheduledThreadPool
 *
 * @author Meredith
 * @date
 */
public class Test006 {

    public static void main(String[] args) {

        ScheduledExecutorService newScheduledThreadPool =Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {

            //i是内部类，不可以直接展示，要申明final
            final int temp=i;
            //execute方法表示启动线程
            newScheduledThreadPool.schedule(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            },3, TimeUnit.SECONDS);
        }
    }
}
