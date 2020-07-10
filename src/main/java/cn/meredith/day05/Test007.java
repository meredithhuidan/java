package cn.meredith.day05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * newSingleThreadExecutor
 *
 * @author Meredith
 * @date
 */
public class Test007 {

    public static void main(String[] args) {

        //单线程
        ExecutorService newSingleThreadExecutor =Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {

            //i是内部类，不可以直接展示，要申明final
            final int temp=i;
            //execute方法表示启动线程
            newSingleThreadExecutor.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName()+","+temp);
                }
            });
        }
        //停止线程池
        newSingleThreadExecutor.shutdown();
    }
}
