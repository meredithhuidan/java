package cn.meredith.day02;

/**
 * 1、什么是线程？ 线程是一条执行路径，每个线程都互不影响
 * 2、什么是多线程？多线程是在一个进程中，有多条不同的执行路径，并行执行，目的是为了提高程序效率。
 * 3、线程的几种分类？
 *      用户线程、守护线程
 *      主线程(main函数中的线程)、子线程、GC线程
 * 4、多线程创建方式？
 *      1）.继承Thread类
 *      2）.实现runnable接口
 *      3）.使用匿名内部类方式
 * 5、线程是使用线程池来进行管理的。
 * 6、面试题：在一个进程中，一定会有什么？主线程。如果连线程主线程都没有，怎么执行程序。
 *
 * @Desc 功能描述：创建多线程例子-Thread类，重写run方法
 * @author Meredith
 */
public class ThreadDemo {

    public static void main(String[] args) {

        /**
         * 想一下，这里是怎么进行执行的？
         * 不是从上到下执行，是交替执行
         */
        System.out.println("main...主线程开始...");
        //创建线程
        CreateTread threadDemo01=new CreateTread();
        //启动线程
        threadDemo01.start();   //注意不是用.run来启动
        for (int i = 0; i < 10; i++) {
            System.out.println("main线程...i:"+i);
        }
        System.out.println("main...主线程结束...");
    }
}

/**
 * 继承Thread类，重写run方法，run方法中，需要线程执行代码
 */
class CreateTread extends Thread{

    /**
     * run方法中，要写线程需要执行的代码
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程...i:"+i);
        }
    }
}
