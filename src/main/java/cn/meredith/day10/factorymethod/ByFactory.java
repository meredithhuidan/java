package cn.meredith.day10.factorymethod;

public class ByFactory implements CarFactory {

    public Car createCar(String name) {
        return new ByCar();
    }
}
