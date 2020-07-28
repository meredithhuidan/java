package cn.meredith.day24.linkedlist;

/**
 * 自定义LinkedList
 *
 * @author Meredith
 * @date
 */
public class ExtLinkedList<E> {

    //链表存储元素大小
    private int size;

    //头节点,为了查找开始
    private Node first;

    //尾节点，为了添加开始
    private Node last;

    /**
     * 链表节点存储元素
     */
    private class Node {
        //节点存放元素的值
        Object object;
        //上一个节点
        Node prev;
        //下一个节点
        Node next;
    }

    /**
     * 添加节点
     *
     * @param e
     */
    public void add(E e) {
        //创建节点
        Node node = new Node();
        //给节点赋值
        node.object = e;
        if (first == null) {
            //添加第一个元素
            //给first,last赋值
            first = node;
        } else {
            //添加第二个或以上元素
            node.prev = last;
            last.next = node;
        }
        last = node;
        size++;
    }

    public void add(int index,E e){
        if (index==size){
            add(e);
        }else {
            checkElementIndex(index);
            Node node = getNode(index);
            if (node != null) {
                Node oldPrev = node.prev;
                Node oldNext = node;

                Node newNode = new Node();
                newNode.object = e;

                if (oldPrev == null) {
                    //插在第一个元素
                    first = newNode;
                } else {
                    oldPrev.next = newNode;
                    newNode.prev = oldPrev;
                }
                newNode.next = oldNext;
                oldNext.prev = newNode;
                size++;
            }
        }
    }

    /**
     * 查询节点：使用下标查询
     * 下标为几，就next几次
     * 可以做二分查找
     *
     * @param index
     * @return
     */
    public Object get(int index) {
        //下标的验证
        checkElementIndex(index);

        return getNode(index).object;
    }

    public Node getNode(int index) {
        Node node = null;
        if (first != null) {
            node = first; //默认取第0个
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("查询越界了");
    }

    /**
     * Tells if the argument is the index of an existing element.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 封装size
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 删除节点：指定下标删除
     *
     * @param index
     * @return
     */
    public boolean remove(int index) {
        checkElementIndex(index);
        //1、先获取当前删除的node节点
        Node oldNode = getNode(index);
        if (oldNode != null) {
            //2、获取删除节点的上下节点
            Node oldNext = oldNode.next;
            Node oldPrev = oldNode.prev;
            if (oldPrev == null)
                //删除第一个元素
                first = oldNext;
            else {
                oldPrev.next = oldNext;
                oldNode.prev = null;
            }

            if (oldNext == null) {
                //删除最后一个元素
                last = oldPrev;
            } else {
                oldNext.prev = oldPrev;
                oldNode.next = null;
            }
            oldNode.object = null;    //让垃圾回收机制可回收
            size--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        ExtLinkedList extLinkedList = new ExtLinkedList<String>();
        extLinkedList.add("a");
        extLinkedList.add("b");
        extLinkedList.add("c");
        extLinkedList.add("d");
        System.out.println(extLinkedList.first.object);
        System.out.println(extLinkedList.first.next.object);
        System.out.println(extLinkedList.first.next.next.object);
        System.out.println("----------------------------");
//        extLinkedList.remove(1);
        extLinkedList.add(4,"h");
        //效率不高，查询算法：二分查找
        for (int i = 0; i < extLinkedList.size; i++) {
            System.out.println(extLinkedList.get(i));
        }
    }

}
