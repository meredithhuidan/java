package cn.meredith.day10.factorymethod;

public class JiLiFactory implements CarFactory{

    public Car createCar(String name) {
        return new JiLiCar();
    }

}
