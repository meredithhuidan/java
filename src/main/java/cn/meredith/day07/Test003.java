package cn.meredith.day07;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * jvm内置缓存
 *
 *
 * @author Meredith
 * @date
 */
public class Test003 {

    private volatile Map<String,String> cache=new HashMap<String, String>();
    //读写锁
    private ReentrantReadWriteLock rwlock=new ReentrantReadWriteLock();
    //写入锁
    private ReentrantReadWriteLock.WriteLock writeLock=rwlock.writeLock();
    //读取锁
    private ReentrantReadWriteLock.ReadLock readLock=rwlock.readLock();

    //读-读能共存，读-写不能共存，写-写不能共存

    //写入元素
    //产生线程不安全问题，脏读
    public void put(String key,String value){
        try {
            writeLock.lock();
            System.out.println("写入put方法 key:" + key + ",value:" + value + ",开始");
            Thread.currentThread().sleep(100);
            cache.put(key, value);
            System.out.println("写入put方法 key:" + key + ",value:" + value + ",结束");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writeLock.unlock();
        }
    }

    //读取元素
    public String  get(String key){
        try {
            readLock.lock();
            System.out.println("读取 key:"+key+",开始");
            Thread.currentThread().sleep(100);
            String value=cache.get(key);
            System.out.println("读取 key:"+key+",结束");
            return value;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {

        final Test003 test003=new Test003();

        //写线程
        Thread t1=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    test003.put("i",i+"");
                }
            }
        });

        //读线程
        Thread t2=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    test003.get(i+"");
                }
            }
        });
        t1.start();
        t2.start();
    }
}
