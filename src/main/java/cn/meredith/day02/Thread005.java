package cn.meredith.day02;

/**
 * 设定优先级:join()方法
 *
 * @author Meredith
 * @date
 */
public class Thread005 {

    public static void main(String[] args) {

        Thread thread=new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 30; i++) {
                    System.out.println("子线程...i:"+i);
                }
            }
        });
        thread.start();
        //主线程需要先让子线程执行完毕,怎么做？
        //调用join方法
        try {
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("主线程...i:"+i);
        }
    }
}
