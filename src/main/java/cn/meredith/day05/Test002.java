package cn.meredith.day05;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 为什么非阻塞式性能更好，却选用阻塞式呢？
 * 阻塞式队列最大的好处：能够防止队列容器溢出，防止丢失数据。
 *
 * 阻塞队列BlockingQueue
 * 存队列的时候，如果满了，就会等待
 * 取队列的时候，如果获取不到，也会等待
 *
 * @author Meredith
 * @date
 */
public class Test002 {

    public static void main(String[] args) throws InterruptedException{

        //阻塞式队列
        //最多支持队列总数
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<String>(3);
        blockingQueue.offer("张三"); //添加非阻塞式队列
        blockingQueue.offer("李四",3, TimeUnit.SECONDS);
        System.out.println(blockingQueue.poll());
        //不会等待。能取到值
        System.out.println(blockingQueue.poll());
        //会等待。取不到值
        System.out.println(blockingQueue.poll(3,TimeUnit.SECONDS));
    }
}
