package cn.meredith.day06.future;

/**
 * 当有线程想要获取RealData的时候，程序会被阻塞。
 * 等到RealData被注入才会使用getReal()方法。
 */
public class FutureData extends Data {

    //读取结果
    private boolean FLAG = false;
    private RealData realData;

    //读取data数据
    public synchronized void setRealData(RealData realData) {
        //如果已经获取到结果，直接返回
        if (FLAG) {
            return;
        }
        //如果FLAG为false,没有获取到数据,传递realData对象
        this.realData = realData;
        FLAG = true;
        notify();
    }

    @Override
    public synchronized String getRequest() {

        while (!FLAG){
            try {
                wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return realData.getRequest();
    }
}
