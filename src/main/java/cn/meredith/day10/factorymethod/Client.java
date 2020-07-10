package cn.meredith.day10.factorymethod;

public class Client {

    public static void main(String[] args) {

        //
//        Car byCar=new ByCar();

        CarFactory carFactory=new ByFactory();
        Car byd=carFactory.createCar("比亚迪");
        byd.run();
    }
}
