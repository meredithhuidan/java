package cn.meredith.day25.hashmap;

import java.util.LinkedList;

/**
 * 基于LinkedList实现hashmap集合
 * jdk1.7时，hashmap使用数组+链表实现
 * <p>
 * 使用hash算法存储  提高效率，直接获取
 * 问题：hashcode碰撞冲突
 * 解决：链表技术 数组的每个元素存放一个链表
 *
 * @author Meredith
 * @date
 */
@SuppressWarnings("")
public class ExtLinkedListHashMap {

    //tables 存放数据元素的 Map存放Entry对象
    LinkedList<Entry>[] tables = new LinkedList[998];

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    public void put(Object key, Object value) {
        Entry entry = new Entry(key, value);

        //两个对象作比较的时候，如果hashcode相同，对象的值不一定相同
        //两个对象作比较的时候，如果equals相同，对象的值一定相同
        int hashCode = key.hashCode();
        //hash算法:hashCode取模，获取余数
        int hash = hashCode % tables.length;

        //1、获取该下标元素是否有linkedlist
        LinkedList<Entry> entryLinkedList = tables[hash];
        if (entryLinkedList == null) {
            //没有hash冲突
            entryLinkedList = new LinkedList<>();
            entryLinkedList.add(entry);
            tables[hash] = entryLinkedList;
        } else {
            //hash碰撞
            for (Entry entryList :
                    entryLinkedList) {
                    if (entryList.key.equals(key)){
                        entryList.value=value;  //直接覆盖
                        return;
                    }
            }
            entryLinkedList.add(entry);
        }
    }

    /**
     * 查询
     * 直接使用hash值直接定位在数组的哪个位置
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        int hashCode = key.hashCode();
        int hash = hashCode % tables.length;
        LinkedList<Entry> linkedList = tables[hash];
        for (Entry entry : linkedList) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        ExtLinkedListHashMap extLinkedListHashMap = new ExtLinkedListHashMap();
        extLinkedListHashMap.put("a", "aaa");
        extLinkedListHashMap.put("a", "bbb");
        extLinkedListHashMap.put("c", "ccc");
        System.out.println(extLinkedListHashMap.get("a"));
        System.out.println(extLinkedListHashMap.get("c"));
    }
}


