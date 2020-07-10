package cn.meredith.day10.abstractfactory;

public class JiLiFactory implements CarFactory {


    public Engine createEngine() {
        return new EngineA();
    }

    public Chair createChair() {
        return new ChairA();
    }
}
