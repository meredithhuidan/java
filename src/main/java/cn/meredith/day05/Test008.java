package cn.meredith.day05;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池
 *
 * @author Meredith
 * @date
 */
public class Test008 {

    public static void main(String[] args) {

        //保证所有真正的线程都能被实际用到。核心线程数---（实际运行线程）、最大线程数---（最多可以创建多少个线程）
        //实际运用的线程<最大线程数 创建一个独立线程
        // 这个线程池表达含义
        // 核心线程数1，最大线程数2，线程空闲超时时间
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(3));
        //任务1 现在已经创建线程，在运行
        threadPoolExecutor.execute(new TaskThread("任务1"));
        //任务2 存放在队列缓存
        threadPoolExecutor.execute(new TaskThread("任务2"));
        //任务3 存放在队列缓存
        threadPoolExecutor.execute(new TaskThread("任务3"));
        //任务4 存放在队列缓存
        threadPoolExecutor.execute(new TaskThread("任务4"));
        //任务5 不大于最大线程数，重新创建线程
        threadPoolExecutor.execute(new TaskThread("任务5"));
        //核心线程数2>=2 最大线程数
        //任务6 拒绝任务执行
        //threadPoolExecutor.execute(new TaskThread("任务6"));
    }
}

class TaskThread extends Thread {

    private String threadName;

    public TaskThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + threadName);
    }
}
