package cn.meredith.day03;

public class Test004 {

    public static void main(String[] args) throws InterruptedException {

        ThreadDemo4 threadDemo = new ThreadDemo4();
        Thread t1 = new Thread(threadDemo, "窗口1");
        Thread t2 = new Thread(threadDemo, "窗口2");
        t1.start();
        Thread.sleep(40);
        threadDemo.flag = false;
        t2.start();
    }
}

/**
 * 模拟出售火车票
 * 两个线程A和B，共享一个全局变量，每个锁不同的。
 * 如果A线程使用非静态同步方法，B线程使用同步代码块。
 * 结论：同步代码块使用this锁，能够与非静态同步方法实现方法
 * ===>非静态同步方法使用this锁
 */
class ThreadDemo4 implements Runnable {

    //同时多个窗口共享100张票
    private int count = 100;
    //同步代码块的全局对象
    private Object object = new Object();

    public boolean flag = true;

    public void run() {
        if (flag) { //使用同步代码块
            //synchronized (object) {
            synchronized (this) {    //使用this明锁
                try {
                    Thread.sleep(50);
                } catch (Exception e) {

                }

                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                    count--;
                }

            }
        } else {    //使用非静态同步方法
            while (count > 0) {
                sale();
            }
        }
    }

    public synchronized void sale() {
        try {
            Thread.sleep(10);
        } catch (Exception e) {

        }
        //要进行判断 t1,t2 要不t2进来不知道票已经卖完了
        if (count > 0) {
            System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
            count--;
        }
    }
}
