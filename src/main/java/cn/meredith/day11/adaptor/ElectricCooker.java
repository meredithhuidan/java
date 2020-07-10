package cn.meredith.day11.adaptor;

/**
 * 电饭煲
 */
public class ElectricCooker {

    private JP110VInterface jp110VInterface;

    public ElectricCooker(JP110VInterface jp110VInterface){
        this.jp110VInterface=jp110VInterface;
    }

    public void cook(){
        jp110VInterface.connect();
        System.out.println("开始做饭...");
    }
}
