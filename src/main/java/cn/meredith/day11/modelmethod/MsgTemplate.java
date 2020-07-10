package cn.meredith.day11.modelmethod;

/**
 * 短信模板
 */
public abstract class MsgTemplate {

    public void sendMsg(){

        //1、开始日志报文
        addHeadLog();
        //2、调用不同具体运行商发送
        httpRequest();
        //3、结束日志报文
        addFootLog();

    }

    private void addFootLog() {
        System.out.println("调用运营商开始记录日志...");
    }

    public abstract void httpRequest();

    private void addHeadLog() {
        System.out.println("调用运营商结束记录日志...");
    }
}
