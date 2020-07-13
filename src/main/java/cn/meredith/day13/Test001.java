package cn.meredith.day13;

/**
 * 垃圾回收机制
 *
 * @author Meredith
 * @date
 */
public class Test001 {

    /**
     * 什么是不可达对象
     * 没有继续被引用 没有存活 没有被继续使用
     *
     * @param args
     */
    public static void main(String[] args) {

        //对象可达
//        Object object1=new Object();
        Test001 object1=new Test001();
        //不可达 提示jvm回收
        object1=null;
//        Object object2=object1;

        //提示给gc进行回收垃圾
        //误区：提示给jvm垃圾回收机制进行回收，但是不代表立即进行回收
        System.gc();
    }

    //finalize 是object类的
    @Override
    protected void finalize() throws Throwable {

        //垃圾回收机制之前会进行执行的方法
        System.out.println("垃圾回收机制要开始执行我的方法了...");

    }
}
