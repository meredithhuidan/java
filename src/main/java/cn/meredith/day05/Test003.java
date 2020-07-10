package cn.meredith.day05;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者问题
 * 之前用wait(),notify()实现
 * 这次用阻塞队列blockingQueue实现
 *
 * @author Meredith
 * @date
 */

/**
 * 生产者 添加队列
 */
class ProducerThread implements Runnable{

    public BlockingQueue<String> blockingQueue;
    public volatile boolean flag=true;
    //保证安全性的计数器
    AtomicInteger atomicInteger=new AtomicInteger();

    public ProducerThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run() {
        System.out.println("生产者线程已经启动");
        try {
            while (flag) {
                String data = atomicInteger.incrementAndGet() + ""; //类似于i++

                boolean offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
                if (offer) {
                    System.out.println("生产者存入队列成功！data:" + data);
                } else {
                    System.out.println("生产者存入队列失败！data:" + data);
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("生产者已经结束...");
        }
    }

    public void stop(){
        this.flag=false;
    }
}

/**
 * 消费者 获取队列
 */
class ConsumerThread implements Runnable{

    public BlockingQueue<String> blockingQueue;
    public volatile boolean flag=true;

    public ConsumerThread(BlockingQueue<String> blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    public void run() {
        System.out.println("消费者线程已经启动");
        try {
            while (flag){
                String data=blockingQueue.poll(2,TimeUnit.SECONDS);
                if (data==null){
                    System.out.println("超过2秒时间，没有获取队列信息");
                    flag=false;
                    return;
                }
                System.out.println("消费者获取到data:"+data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("消费者线程已结束...");
        }

    }
}

public class Test003 {

    public static void main(String[] args) {

        //LinkedBlockingQueue阻塞队列大小的配置是可选的，
        // 如果我们初始化时指定一个大小，它就是有边界的，如果不指定，它就是无边界的。
        // 说是无边界，其实是采用了默认大小为Integer.MAX_VALUE的容量 。它的内部实现是一个链表。
        BlockingQueue<String> blockingQueue=new LinkedBlockingQueue<String>(10);
        ProducerThread producerThread=new ProducerThread(blockingQueue);
        ConsumerThread consumerThread=new ConsumerThread(blockingQueue);
        Thread thread1=new Thread(producerThread);
        Thread thread2=new Thread(consumerThread);
        thread1.start();
        thread2.start();
        try {
            //等待10s
            Thread.sleep(1000*10);
        }catch (Exception e){
            e.printStackTrace();
        }
        producerThread.stop();

    }
}
