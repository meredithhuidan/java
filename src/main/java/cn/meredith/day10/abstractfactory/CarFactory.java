package cn.meredith.day10.abstractfactory;

/**
 * 包装厂
 * 零部件的包装厂
 *
 */
public interface CarFactory {

    //创建发动机
    Engine createEngine();

    //创建座椅
    Chair createChair();
}
