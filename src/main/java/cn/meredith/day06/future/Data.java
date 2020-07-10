package cn.meredith.day06.future;

/**
 * Future模式
 * 异步加载
 * 类似前端的ajax
 *
 * @author Meredith
 * @date
 */
//公共data数据结果
public abstract class Data{

    //方法作用 返回线程执行结果
    public abstract String getRequest();
}
