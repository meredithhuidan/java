package cn.meredith.day14;

/**
 * 新生代比例参数配置
 * 使用示例:-Xms20m -Xmx20m -Xmn1m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * 说明：堆内存初始化值20m,堆内存最大值20m，新生代最大值可用1m，eden空间和from/to空间的比例为2/1
 *
 * 设置新生代与老年代比例参数
 * -XX:NewRatio=2
 * 新生代和老年代的占比为1/2
 *
 * @author Meredith
 * @date
 */
public class Test002 {

    public static void main(String[] args) {

        Byte[] b=null;
        for (int i = 0; i < 10; i++) {
            b=new Byte[1*1024*1024];
        }
    }
}
