package cn.meredith.day05;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 为什么非阻塞式性能更好，却选用阻塞式呢？
 * 阻塞式队列最大的好处：能够防止队列容器溢出，防止丢失数据。
 * 非阻塞队列ConcurrentLinkedQueue,并发包java.util.concurrent下
 *
 * @author Meredith
 * @date
 */
public class Test001 {

    public static void main(String[] args) {

        //非阻塞队列，无界队列
        //使用无界队列的时候，
        ConcurrentLinkedQueue<String> concurrentLinkedQueue=new ConcurrentLinkedQueue<String>();
        //添加队列
        concurrentLinkedQueue.offer("张三");
        concurrentLinkedQueue.offer("李四");
        concurrentLinkedQueue.offer("吴乙");
        concurrentLinkedQueue.add("薛白");
        //获取队列，只能获取一个元素，同时把取出的值删除
        System.out.println(concurrentLinkedQueue.poll());
        System.out.println(concurrentLinkedQueue.poll());
        //也是获取队列，但不会把取出的值删掉,一般不使用这种，会没有新的元素进来
        System.out.println(concurrentLinkedQueue.peek());
        //获取队列个数
        System.out.println(concurrentLinkedQueue.size());

    }
}
