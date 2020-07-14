package cn.meredith.day14;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出问题
 * 解决办法:设置堆内存大小
 * -Xms1m -Xmx10m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author Meredith
 * @date
 */
public class Test003 {

    //垃圾回收基本原则：内存不足时候会去回收，内存如果足够，暂时不会去回收
    public static void main(String[] args) {

        List<Object> listObject = new ArrayList<Object>();
        for (int i = 0; i < 10; i++) {
            System.out.println("i:" + i);
            Byte[] bytes = new Byte[1 * 1024 * 1024];
//            listObject.add(bytes);
        }
    }
}
