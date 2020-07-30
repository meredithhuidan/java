package cn.meredith.day26.hashmap;

/**
 * 自定义Map接口
 *
 * @author Meredith
 * @date
 */
public interface ExtMap<K,V> {

    //插入元素
    public V put(K k,V v);

    //查询元素
    public V get(K k);

    //获取集合元素个数
    public int size();

    /**
     * Entry的作用---Node节点
     * @param <K>
     * @param <V>
     */
    interface Entry<K,V>{

        K getKey();
        V getValue();
        V setValue(V value);
    }
}
