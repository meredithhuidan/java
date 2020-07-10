package cn.meredith.day06.future;

public class Main {

    public static void main(String[] args) {

        FutureClient futureClient=new FutureClient();
        Data request=futureClient.request("666666");
        System.out.println("main.数据发送成功");
        System.out.println("主线程执行其他任务");
        String result=request.getRequest();
        System.out.println("主线程获取result:"+result);
    }
}
