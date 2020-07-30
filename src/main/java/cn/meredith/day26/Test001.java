package cn.meredith.day26;

import cn.meredith.day26.hashmap.ExtHashMap;

public class Test001 {

    public static void main(String[] args) {

        ExtHashMap extHashMap=new ExtHashMap();
        extHashMap.put("1号","aaaaaa");
        extHashMap.put("2号","bbbbbb");
        extHashMap.put("3号","aaaaaa");
        extHashMap.put("4号","bbbbbb");
        extHashMap.put("5号","aaaaaa");
        extHashMap.put("6号","bbbbbb");
        extHashMap.put("7号","baaaaa");
        extHashMap.put("14号","bbbbbb");
        System.out.println("修改3号之前：----------------------------------------");
        extHashMap.print();
        System.out.println("修改3号之后：----------------------------------------");
        extHashMap.put("3号","333333");
        extHashMap.print();
        System.out.println("---------------------");
        System.out.println(extHashMap.get("14号"));
    }
}
