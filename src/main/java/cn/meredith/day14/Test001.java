package cn.meredith.day14;

/**
 * 配置堆内存大小
 *
 * @author Meredith
 * @date
 */
public class Test001 {

    public static void main(String[] args) {

        Byte[] b=new Byte[2*1024*1024];
        System.out.println("分配4M空间给数组");

        System.out.println("堆最大内存"+Runtime.getRuntime().maxMemory()/1024/1024+"M");
        System.out.println("堆可用内存"+Runtime.getRuntime().freeMemory()/1024/1024+"M");
        //已经使用内存就是初始内存设置值
        System.out.println("堆已经使用内存"+Runtime.getRuntime().totalMemory()/1024/1024+"M");
    }
}
