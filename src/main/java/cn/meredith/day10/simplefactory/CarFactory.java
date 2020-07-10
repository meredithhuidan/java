package cn.meredith.day10.simplefactory;

import org.apache.commons.lang3.StringUtils;

/**
 * 汽车厂
 */
public class CarFactory {

    public static Car createCar(String name){
        //判断name是否为空
        if (StringUtils.isEmpty(name)){
            return null;
        }
        if (name.equals("比亚迪")){
            return new ByCar();
        }
        //改代码之后，可以不用重启数据库，该怎么做？
        //动态  存放缓存或数据库+反射。 类型id,类型名称，类型class地址
        if (name.equals("吉利")){
            return new JiLiCar();
        }
        //其他或者未找到
        return null;
    }
}
