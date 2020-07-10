package cn.meredith.day06;

class Test extends Thread {

    //执行下载请求（大概耗时10mins）
    @Override
    public void run() {
        //下载请求.....
        //如果想获得run方法执行结果，必须执行完毕才能获取到结果，整个程序是阻塞的
    }


}

public class Test002 {

    public static void main(String[] args) {
        Test test=new Test();
        test.start();
        //主线程如何知道子线程程序执行完毕

    }
}


