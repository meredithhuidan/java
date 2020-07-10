package cn.meredith.day07;

/**
 * 两个线程，同时操作一个全局变量，产生线程安全问题
 * 解决：用悲观锁synchronized
 *
 * @author Meredith
 * @date
 */
public class Test004 implements Runnable{

    //共享的全局变量
    private static int count=1;

    public void run() {
         while (true){
             Integer count=getCount();
             if (count>=170){
                 break;
             }
             System.out.println(count);
         }
    }

    //synchronized 具有可重入，保证原子性和可见性。影响效率，不能解决重排序问题
    public synchronized Integer getCount(){
        try {
            Thread.sleep(50);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count++;
    }

    public static void main(String[] args) {

        Test004 test004=new Test004();
        Thread t1=new Thread(test004);
        Thread t2=new Thread(test004);
        t1.start();
        t2.start();
    }
}
