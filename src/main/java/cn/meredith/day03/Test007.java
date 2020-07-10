package cn.meredith.day03;

/**
 * Threadlocal使用
 * 给每个线程提供局部变量，以此提高线程安全
 * Threadlocal底层是一个Map集合
 *
 */
class Res {
    //public Integer count = 0;
    public static ThreadLocal<Integer> threadLocal=new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0;
        }
    };

    //生成序列号
    public Integer getNumber() {
        int count=threadLocal.get()+1;
        threadLocal.set(count);
        return count;
    }
}

public class Test007 extends Thread {

    private Res res;

    //构造函数
    public Test007(Res res) {
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + "," + res.getNumber());
        }

    }

    public static void main(String[] args) {
        Res res=new Res();
         Test007 t1=new Test007(res);
        Test007 t2=new Test007(res);
        t1.start();
        t2.start();

    }
}

