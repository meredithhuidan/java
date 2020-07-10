package cn.meredith.day12.strategy;

/**
 * 策略模式定义的一个抽象公共算法
 */
 abstract class Strategy {

    abstract void algorithmInterface();
}

/**
 * 初级会员 针对A算法
 */
class StrategyA extends Strategy{
    @Override
    void algorithmInterface() {
        System.out.println("初级会员 针对A算法");
    }
}

class StrategyB extends Strategy{
    @Override
    void algorithmInterface() {
        System.out.println("中级会员 针对B算法");
    }
}

class StrategyC extends Strategy{
    @Override
    void algorithmInterface() {
        System.out.println("高级会员 针对C算法");
    }
}

/**
 * 包装一层
 * 隐藏具体算法
 */
class Context{
    private Strategy strategy;

    Context(Strategy strategy){
        this.strategy=strategy;
    }

    void algorithmInterface(){
        strategy.algorithmInterface();
    }
}

public class StrategyTest{

    public static void main(String[] args) {

        Context context=null;

        Strategy strategyA=new StrategyA();
        context=new Context(strategyA);
        context.algorithmInterface();

        Strategy strategyB=new StrategyB();
        context=new Context(strategyB);
        context.algorithmInterface();

        Strategy strategyC=new StrategyC();
        context=new Context(strategyC);
        context.algorithmInterface();
    }
}
