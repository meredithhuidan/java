package cn.meredith.day23;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ArrayList
 * 底层采用数组方式
 * 保证集合无限大---数组扩容技术
 * 1)Arrays.copyOf
 * 2)System.arraycopy
 */
public class Test001 {

    public static void main(String[] args) {

        Object[] objects = {1, 2};
        //将原来数组长度扩容为10，原来本身的数据不变
        Object[] newObjects = Arrays.copyOf(objects, 10);

        //src原数组 srcPos起始位置 dest目标数组 destPos目标位置 length复制长度
        int[] from = new int[]{0, 1, 2, 3, 4, 5, 6};
        int[] to = new int[3];
        System.arraycopy(from, 3, to, 0, 3);
        for (int i = 0; i < to.length; i++) {
            System.out.println(to[i]);
        }

        //可以自己复制自己
        System.out.println("---------------------------------------");
        int[] fun = new int[]{0, 1, 2, 3, 4, 5, 6};
        System.arraycopy(fun, 0, fun, 3, 3);
        for (int i = 0; i < fun.length; i++) {
            System.out.println(fun[i]);
        }

        //jdk1.7之后，数组默认大小存放在add()里面
        //先看构造函数，再看方法
        //1、arraylist底层采用数组实现，数组名称elementData
        //2、arraylist底层数组默认初始化容量是10
        ArrayList arrayList = new ArrayList();
        arrayList.add("张三");
        arrayList.get(0);
        arrayList.remove(0);
        arrayList.remove("张三");
    }
}
