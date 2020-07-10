package cn.meredith.day04;

/**
 * 多线程之间通讯
 * 生产者线程，消费者线程
 *
 * 解决引发的线程安全问题：读写线程都加synchronized
 *
 * @author Meredith
 * @date
 */
//共享对象
class Res{

    public String name;
    public String sex;
}

//写入线程，即生产者线程
class IntThread extends Thread{

    public Res res;

    public IntThread(Res res){
        this.res=res;
    }

    @Override
    public void run() {

        int count=0;
        while (true) {
            synchronized (res) {
                if (count == 0) {
                    res.name = "小红";
                    res.sex = "女";
                } else {
                    res.name = "小军";
                    res.sex = "男";
                }
                count = (count + 1) % 2;
            }
        }
    }
}

//消费者线程
class OutThread extends Thread{

    public Res res;

    public OutThread(Res res){
        this.res=res;
    }

    @Override
    public void run() {
        //读取数据
        while (true) {
            synchronized (res) {
                System.out.println(res.name + "," + res.sex);
            }
        }
    }
}

public class Test001 {

    public static void main(String[] args) {

        Res res=new Res();
        IntThread intThread=new IntThread(res);
        OutThread outThread=new OutThread(res);
        intThread.start();
        outThread.start();
    }
}
