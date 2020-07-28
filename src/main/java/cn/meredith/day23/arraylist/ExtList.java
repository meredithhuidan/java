package cn.meredith.day23.arraylist;

/**
 * 自定义List接口
 */
public interface ExtList<E> {

    public void add(E e);
    public int getSize();
    public E get(int index);
}
