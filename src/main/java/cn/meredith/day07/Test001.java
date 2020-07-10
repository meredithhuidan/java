package cn.meredith.day07;

/**
 * 重入锁
 * 轻量级（Lock锁）与重量级锁（synchronized锁）----可重入性（递归锁）
 * 证明synchronized锁有可重入性
 *
 * @author Meredith
 * @date
 */
public class Test001 implements Runnable{

    public void run() {
        set();
    }

    //synchronized什么时候释放锁？代码结束
    public synchronized void set(){
        System.out.println("set方法");
        get();
    }

    public synchronized void get(){
        System.out.println("synchronized 可以具有重入性--get方法");
    }

    public static void main(String[] args) {

        Test001 test001=new Test001();
        Thread thread=new Thread(test001);
        thread.start();
    }
}
