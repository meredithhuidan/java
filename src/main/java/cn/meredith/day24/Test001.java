package cn.meredith.day24;

import java.util.LinkedList;

/**
 * LinkedList
 * transient int size = 0;      //集合大小
 * transient Node<E> first;     //第一个节点  开始查询
 * transient Node<E> last;      //最后一个节点 添加元素开始
 */
public class Test001 {

    public static void main(String[] args) {

        //和Arraylist集合非常相似
        LinkedList linkedList=new LinkedList<String>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.get(1);
        linkedList.remove(1);
        linkedList.add(1,"f");
        System.out.println("size:"+linkedList.size());
        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        }
    }
}
