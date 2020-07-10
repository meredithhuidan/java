package cn.meredith.day03;

/**
 * 多线程安全模拟演示
 * 模拟抢火车票
 * 用synchronized
 * 在使用同步的时候，锁一定要使用同一把锁
 *
 * @author Meredith
 * @data
 */
public class Test003 {

    public static void main(String[] args) {

        ThreadDemo3 threadDemo01=new ThreadDemo3();
        //改变，会发生什么效果
        ThreadDemo3 threadDemo02=new ThreadDemo3();
        Thread t1=new Thread(threadDemo01,"窗口1");
        Thread t2=new Thread(threadDemo02,"窗口2");
        t1.start();
        t2.start();
    }
}
/**
 * 模拟出售火车票
 */
class ThreadDemo3 implements Runnable{

    //出现出售同一张票问题，应该加static，变成静态变量

    //同时多个窗口共享100张票
    private static int count=100;
    //同步代码块的全局对象
    private static Object object=new Object();

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