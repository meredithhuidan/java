package cn.meredith.day06;

import java.util.concurrent.*;

/**
 * newCachedThreadPool.submit()
 * Callable,future模式
 * Callable用来执行任务，产生结果，而Future用来获得结果。
 *
 * @author Meredith
 * @date
 */
public class Test003 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService newCachedThreadPool= Executors.newCachedThreadPool();
        final Future<String> submit =newCachedThreadPool.submit(new TaskFuture());
        System.out.println("1、主线程开始执行....");
        //获取执行结果
        //获取异步执行的结果，如果没有结果可用，此方法会阻塞直到异步计算完成。
        new Thread(new Runnable() {
            public void run() {
                try {
                    String result=submit.get();
                    System.out.println("2、result:"+result);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("主线程开始执行...");
    }
}

class TaskFuture implements Callable<String>{

    public String call() throws Exception {

        System.out.println("3、正在执行任务，需要等待五秒时间，执行任务开始");
        Thread.sleep(5000);
        System.out.println("4、正在执行任务，需要等待五秒时间，执行任务结束");
        return "薛白";
    }
}
