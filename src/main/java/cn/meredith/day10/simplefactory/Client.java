package cn.meredith.day10.simplefactory;

public class Client {

    public static void main(String[] args) {

        //
//        Car byCar=new ByCar();

        Car byd=CarFactory.createCar("比亚迪");
        byd.run();
    }
}
