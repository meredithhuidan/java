package cn.meredith.day03;

/**
 * java内存模型 volatile关键字
 * 及时刷新本地内存与主内存的值
 * 来保证线程可见
 *
 * @author Meredith
 * @date
 */
class ThreadDemo8 extends Thread{

    public volatile boolean flag=true;

    //一直读的是本地内存里面的flag
    @Override
    public void run() {
        System.out.println("线程开始...");
        while (flag){

        }
        System.out.println("线程结束...");
    }

    public void setRunning(Boolean flag){
        this.flag=flag;
    }
}

public class Test008 {

    public static void main(String[] args) throws InterruptedException {

        ThreadDemo8 threadDemo8=new ThreadDemo8();
        threadDemo8.start();
        Thread.sleep(3000);
        //只是主线程改了flag，也就是只有主内存的值改了，没有更新到本地内存
        threadDemo8.setRunning(false);  //主线程
        System.out.println("flag已经改为false");
        Thread.sleep(1000);
        System.out.println("flag:"+threadDemo8.flag);

    }
}
