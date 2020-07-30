package cn.meredith.day23.arraylist;

import java.util.Arrays;

/**
 * 自定义ArrayList
 * 定义为泛型
 *
 * @author Meredith
 * @date
 */
public class ExtArrayList<E> implements ExtList<E> {

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 记录ArrayList实际长度
     */
    private int size;

    //底层采用数组存放
    private Object[] elementData;

    /**
     * 指定 数组初始化容量
     *
     * @param initialCapacity
     * @throws IllegalAccessException
     */
    public ExtArrayList(int initialCapacity) throws IllegalAccessException {
        if (initialCapacity < 0) {
            throw new IllegalAccessException("初始容量不能小于0");
        }
        elementData = new Object[initialCapacity];
    }

    /**
     * 默认数组初始化容量为10
     */
    public ExtArrayList() throws IllegalAccessException {
        this(DEFAULT_CAPACITY);
    }

    /**
     * add(),及扩容
     * ArrayList底层每次扩容1.5倍
     *
     * @param e
     */
    public void add(E e) {
        //1、判断实际存放的数据容量是否大于初始化容量;如果大于，进行扩容
        ensureExplicitCapacity(size + 1);
        //2、使用下标进行赋值
        elementData[size++] = e;
    }

    public void add(int index, Object object) {
        ensureExplicitCapacity(size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, 1);
        elementData[index] = object;
        size++;
    }

    /**
     * minCapacity 为size+1
     *
     * @param minCapacity
     */
    private void ensureExplicitCapacity(int minCapacity) {

        if (size == elementData.length) {

            //新数组容量大小
//            int newCapacity = 2 * size;
            //扩容1.5倍
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);

            //如果初始容量为1，那么扩容后大小为？ 最小扩容大小
            if (newCapacity - minCapacity < 0) {
                newCapacity = minCapacity;
            }

            //将旧数组的值赋值到新数组中去
            elementData = Arrays.copyOf(elementData, newCapacity);
            //产生新数组，占内存，不推荐
//            Object[] newObjects = new Object[newCapacity];
//            for (int i = 0; i < elementData.length; i++) {
//                newObjects[i] = elementData[i];
//            }
//            elementData = newObjects;
        }
    }

    /**
     * 使用下标获取数组元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    E elementData(int index){
        return (E) elementData[index];
    }

    /**
     * 判断数组越界与否
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException("数组越界了！");
    }

    /**
     * List长度
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 删除指定下标的元素
     *
     * @param index
     * @return
     */
    public Object remove(int index) {
        //使用下标查询，该值是否存在
        Object object = get(index);
        int numMoved = size - index - 1;
        //底层删除原理分析
        //使用System.arraycopy往前移动数据，将最后一个变为null
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        elementData[--size] = null;   //将最后一个元素变为null
        return object;
    }

    /**
     * 按对象删除
     * 相同元素只删除第一个
     *
     * @param object
     * @return
     */
    public boolean remove(Object object) {
        for (int i = 0; i < size; i++) {
            Object value = elementData[i];
            if (value.equals(object)) {
                remove(i);
                return true;
            }
        }
        return false;
    }
}
