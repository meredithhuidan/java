package cn.meredith.day25.hashmap;

/**
 * hashmap存储对象
 *
 * @param <Key>
 * @param <Value>
 */
public class Entry<Key, Value> {

    Key key;
    Value value;

    public Entry(Key key, Value value) {
        this.key = key;
        this.value = value;
    }
}
