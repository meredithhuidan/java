package cn.meredith.day05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * newCachedThreadPool
 *
 * @author Meredith
 * @date
 */
public class Test004 {

    public static void main(String[] args) {

        //可缓存的线程池 可以重复利用 线程池是无限大小的
        ExecutorService newCachedThreadPool= Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {

            //i是内部类，不可以直接展示，要申明final
            final int temp=i;
            //execute方法表示启动线程
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }

    }
}
