package cn.meredith.day25.hashmap;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于Arraylist实现hashMap集合
 *
 * @author Meredith
 * @date
 */
public class ExtArrayListHashMap<Key, Value> {

    //key,value

    //hashmap 存储容器
    private List<Entry<Key, Value>> tables = new ArrayList<>();

    //map容器的实际容量
    private int size;

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        Entry entry = getEntry(key);
        if (value != null) {
            //已经存在
            entry.value = value;
        } else {
            //1、定义hashmap容器
            //2、调用put时，将该hash存储对象存入到ArrayList中
            Entry newEntry = new Entry<>(key, value);
            tables.add(entry);
        }
    }

    /**
     * 查询元素
     *
     * @param key
     * @return
     */
    public Value get(Key key) {
        Entry<Key, Value> entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    public Entry<Key, Value> getEntry(Key key) {
        //从头查询到尾 作优化
        for (Entry entry : tables) {
            if (entry.key.equals(key)) {
                return entry;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ExtArrayListHashMap hashMap = new ExtArrayListHashMap<>();
        hashMap.put("a", "aaaaa");
        hashMap.put("b", "bbbbb");
        hashMap.put("a", "ccccc");   //如果key相同的话，value会进行覆盖
        System.out.println(hashMap.get("a"));
    }

}


