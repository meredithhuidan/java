package cn.meredith.day03;

/**
 * 多线程死锁问题
 * t1 先获取oj锁，再获取this锁；（在获取oj锁）(锁重入，已经获取的锁不需要再获取了)
 * t2 先获取this锁，再获取oj锁
 * 产生原因：同步中嵌套同步
 *
 * @author Meredith
 * @date
 */
class ThreadDemo6 implements Runnable{

    private int count=100;
    public boolean flag=true;
    Object oj=new Object();
    public void run() {
        if (flag) { //使用同步代码块
            synchronized (oj) {    //使用当前class字节码文件
                sale();
            }
        } else {    //使用静态同步方法
            while (count > 0) {
                sale();
            }
        }
    }


    public synchronized void sale(){
        synchronized (oj) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {

            }

            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                count--;
            }
        }
    }
}

public class Test006 {

    public static void main(String[] args) throws InterruptedException{

        ThreadDemo6 threadDemo6=new ThreadDemo6();
        Thread t1=new Thread(threadDemo6,"窗口1");
        Thread t2=new Thread(threadDemo6,"窗口2");
        t1.start();
        Thread.sleep(40);
        threadDemo6.flag=false;
        t2.start();
    }
}


