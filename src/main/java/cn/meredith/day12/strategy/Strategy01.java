package cn.meredith.day12.strategy;

public class Strategy01 {

    //会员 3种级别 铂金会员 黄金会员 普通会员
    public void getPrice(String level){
        Long price=null;    //价格转换成 分

        //不要这样写，扩展性很差，违背开闭原则，需要重构
        if (level.equals("铂金会员")){
            //只需要100元
            price= Long.valueOf(100*100);
        }else if (level.equals("黄金会员")){
            price= Long.valueOf(100*150);
        }else if (level.equals("普通会员")){
            price= Long.valueOf(100*200);
        }
    }
}
