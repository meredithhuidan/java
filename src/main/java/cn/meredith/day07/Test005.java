package cn.meredith.day07;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 两个线程，同时操作一个全局变量，产生线程安全问题
 * 解决：乐观锁AtomicInteger
 *
 */
public class Test005 implements Runnable{

    //共享的全局变量
    //private static int count=1;
    private AtomicInteger atomicInteger=new AtomicInteger();//线程安全

    public void run() {
        while (true){
            Integer count=getCount();
            if (count>=170){
                break;
            }
            System.out.println(count);
        }
    }

    public  Integer getCount(){
        try {
            Thread.sleep(50);
        }catch (Exception e){
            e.printStackTrace();
        }
        //return count++;
        //类似i++
        return atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) {

        Test005 test005=new Test005();
        Thread t1=new Thread(test005);
        Thread t2=new Thread(test005);
        t1.start();
        t2.start();
    }
}
