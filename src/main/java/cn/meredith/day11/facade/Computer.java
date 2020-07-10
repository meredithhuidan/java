package cn.meredith.day11.facade;

/**
 * 做个封装
 * 门面类
 */
public class Computer {

    private  EamilSmsService eamilSmsService;
    private SmMsgService smMsgService;
    private WeiXinMsgService weiXinMsgService;

    public Computer(){
       eamilSmsService=new EamilSmsServiceImp();
        smMsgService=new SmMsgServiceImp();
        weiXinMsgService=new WeiXinMsgServiceImp();
    }

    public void send(){
        eamilSmsService.send();
        smMsgService.send();
        weiXinMsgService.send();
    }
}
