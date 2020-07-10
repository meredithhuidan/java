package cn.meredith.day04;

/**
 * 多线程之间通讯
 * 生产者线程，消费者线程
 *  Test001产生会一次性读很多的问题，想让“写一次，读一次”
 *  wait(),notify()使用
 *
 * @author Meredith
 * @date
 */
//共享对象
class Res1{

    public String name;
    public String sex;
    //flag为true,允许读，但不能读
    //flag为false,允许写，不允许读
    public Boolean flag=false;
}

//写入线程，即生产者线程
class IntThread1 extends Thread{

    public Res1 res;

    public IntThread1(Res1 res){
        this.res=res;
    }

    @Override
    public void run() {

        int count=0;
        while (true) {
            synchronized (res) {
                try {
                    if (res.flag) {
                        //释放当前锁对象
                        res.wait();
                    }
                    Thread.sleep(1000);
                }catch (Exception e) {
                }

                if (count == 0) {
                    res.name = "小红";
                    res.sex = "女";
                } else {
                    res.name = "小军";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
                //标记当前线程为等待
                res.flag=true;
                //唤醒被等待的线程
                res.notify();
            }
        }
    }
}

//消费者线程
class OutThread1 extends Thread{

    public Res1 res;

    public OutThread1(Res1 res){
        this.res=res;
    }

    @Override
    public void run() {
        //读取数据
        while (true) {

            synchronized (res) {
                if (!res.flag){
                    try {
                        res.wait();
                    }catch (Exception e){

                    }
                }
                System.out.println(res.name + "," + res.sex);
                res.flag=false;
                res.notify();
            }
        }
    }
}

public class Test002 {

    public static void main(String[] args) {

        Res1 res=new Res1();
        IntThread1 intThread=new IntThread1(res);
        OutThread1 outThread=new OutThread1(res);
        intThread.start();
        outThread.start();
    }
}
