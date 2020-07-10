package cn.meredith.day10.abstractfactory;

public class Client {

    public static void main(String[] args) {
        CarFactory carFactory=new JiLiFactory();
        Engine engine=carFactory.createEngine();
        Chair chair=carFactory.createChair();
        engine.run();
        chair.run();
    }
}
