package cn.meredith.day04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程之间通讯
 * 生产者线程，消费者线程
 * Lock锁
 *
 * @author Meredith
 * @date
 */
//共享对象
class Ress {
    public String userName;
    public  String gender;
    public boolean flag=false;
    //lock锁
    public Lock lock=new ReentrantLock();
    //condition
    public Condition condition=lock.newCondition();
}

//生产者线程
class InputThread extends Thread{

    public Ress ress;

    public InputThread(Ress ress){
        this.ress=ress;
    }

    @Override
    public void run() {
        int count=0;
        while (true)
        {
            try {
                ress.lock.lock();   //上锁
                if (ress.flag) {     //flag为true，允许读，不允许写
                    ress.condition.await();
                }
                if (count == 0) {
                    ress.userName = "小红";
                    ress.gender = "女";
                } else {
                    ress.userName = "小军";
                    ress.gender = "男";
                }
                count = (count + 1) % 2;
                ress.flag=true;
                ress.condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ress.lock.unlock(); //一定要在finally中释放锁
            }
        }
    }
}

//消费者线程
class OutputThread extends Thread{

    Ress ress;

    public OutputThread(Ress ress){
        this.ress=ress;
    }
    @Override
    public void run() {
        while (true){
            try {
                ress.lock.lock();   //上锁
                if (!ress.flag) {   //flag为false,只能写，不能读
                    ress.condition.await();
                }
                Thread.sleep(1000);
                System.out.println(ress.userName+","+ress.gender);
                ress.flag=false;
                ress.condition.signal();

            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ress.lock.unlock();
            }
        }
    }
}

public class ThreadLock {

    public static void main(String[] args) {

        Ress ress=new Ress();
        //另一种写法
        //Condition condition=ress.lock.newCondition();
        //InputThread inputThread=new InputThread(ress,condition);
        InputThread inputThread=new InputThread(ress);
        OutputThread outputThread=new OutputThread(ress);
        inputThread.start();
        outputThread.start();
    }
}
