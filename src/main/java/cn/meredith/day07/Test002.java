package cn.meredith.day07;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁
 * 轻量级（Lock锁）与重量级锁（synchronized锁）----可重入性（递归锁）
 * 证明Lock锁有可重入性
 *
 * @author Meredith
 * @date
 */
public class Test002 extends Thread{

    Lock lock=new ReentrantLock();

    @Override
    public void run() {
        set();
    }

    public void set(){
        try {
            lock.lock();
            System.out.println("Lock 可以具有可重入性--set方法");
            get();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void get(){
        try {
            lock.lock();
            System.out.println("get方法");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        Test002 test002=new Test002();
        test002.start();
    }
}
