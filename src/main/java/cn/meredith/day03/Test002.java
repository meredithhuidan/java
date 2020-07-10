package cn.meredith.day03;

/**
 * 多线程安全模拟演示
 * 模拟抢火车票
 * 用synchronized
 * 2、同步代码块,
 *
 * @author Meredith
 * @data
 */
public class Test002 {

    public static void main(String[] args) {

        ThreadDemo2 threadDemo=new ThreadDemo2();
        Thread t1=new Thread(threadDemo,"窗口1");
        Thread t2=new Thread(threadDemo,"窗口2");
        t1.start();
        t2.start();
    }
}

/**
 * 模拟出售火车票
 */
class ThreadDemo2 implements Runnable{

    //同时多个窗口共享100张票
    private int count=100;
    //同步代码块的全局对象
    private Object object=new Object();

    public void run() {
        try{
            Thread.sleep(50);
        }catch (Exception e){

        }
        while (count>0){
            sale();
        }
    }
    //可能会发生线程安全问题的地方，用synchronized
    //2、同步代码块方式
    public  void sale(){
        //要进行判断 t1,t2 要不t2进来不知道票已经卖完了
        synchronized(object) {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + ",出售" + (100 - count + 1) + "张票");
                count--;
            }
        }
    }
}