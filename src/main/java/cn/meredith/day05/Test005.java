package cn.meredith.day05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * newFixedThreadPool
 *
 * @author Meredith
 * @date
 */
public class Test005 {

    public static void main(String[] args) {

        //可固定长度线程池
        ExecutorService newFixedThreadPool =Executors.newFixedThreadPool(3);

        for (int i = 0; i < 20; i++) {

            //i是内部类，不可以直接展示，要申明final
            final int temp=i;
            //execute方法表示启动线程
            newFixedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }
    }
}
