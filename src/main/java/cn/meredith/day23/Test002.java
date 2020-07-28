package cn.meredith.day23;

import cn.meredith.day23.arraylist.ExtArrayList;
import cn.meredith.day23.arraylist.ExtList;

public class Test002 {

    public static void main(String[] args) throws IllegalAccessException {
        ExtArrayList extArrayList = new ExtArrayList(1);
        extArrayList.add("张三");
        extArrayList.add("李四");
        extArrayList.add("王五");
        System.out.println(extArrayList.get(0));
        System.out.println("-----------------------");
        extArrayList.remove(0);
        extArrayList.remove("李四");
        extArrayList.add(0, "薛白");
        for (int i = 0; i < extArrayList.getSize(); i++) {
            System.out.println(extArrayList.get(i));
        }

        System.out.println("----------------------------");
        //反射机制不能获取泛型类型
        //通过字节码技术获取
        ExtList<String > list=new ExtArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }
    }

}
