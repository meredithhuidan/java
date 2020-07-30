package cn.meredith.day26.hashmap;

/**
 * 自定义HashMap
 * 数组+链表（单链表）
 *
 * @author Meredith
 * @date
 */
public class ExtHashMap<K,V> implements ExtMap<K,V> {

    //1、定义table 存放hashmap数组元素; 默认没有初始化容器---懒加载功能
    Node<K,V>[] table=null;

    //2、实际用到的table存储容器大小
    int size;

    //3、负载因子 0.75 ； 扩容的时候，才会用到 ；
    // 负载因子越小，hash冲突越少
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    //4、table默认初始化大小
    static  int DEFAULT_INITIAL_CAPACITY = 1 << 4; //16

    @Override
    public V put(K key, V value) {

        //1、判断table 数组大小是否为空（如果为空的情况下，做初始化操作）
        if (table==null){
            table=new Node[DEFAULT_INITIAL_CAPACITY];
        }
        //2、判断数组是否需要扩容
        //为什么扩容？扩容数组后有什么影响？
        //实际存储大小=负载因子*初始容量=0.75*16=12
        //即如果size>12，就需要扩容，扩容之前的两倍
        if (size>(DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR)){
            //需要对table数组进行扩容
            resize();
        }

        //hashmap 源码 如果key为null ,插入到index=0的位置
        //3、计算hash值指定下标位置
        int index=getIndex(key,DEFAULT_INITIAL_CAPACITY);
        Node<K,V> node=table[index];
        if (node==null){
            //没有发生hash冲突
            node=new Node<>(key,value,null);
            size++;
        }else {
            //hash冲突
            //遍历所有node,如果没有，添加node;如果有，就修改
            Node<K,V> newNode=node;
            while (newNode!=null){
                //判断是值相同，hashcode相同；还是值不同，hashcode相同？
                if (newNode.getKey().equals(key) || newNode.getKey()==key){
                    //值相同，修改
                    return newNode.setValue(value);
                }else {
                    //值不同，添加node;
                    //新的node.next指向旧的node;新的排在最前面；后进先出原则
                    if (newNode.next==null) {
                        //遍历到最后一个node，没有，添加node
                        node = new Node<>(key, value, node);
                        size++;
                    }
                }
                newNode=newNode.next;
            }

        }
        table[index]=node;
        return null;
    }

    private void resize(){
        //1、生成新的table 之前两倍大小
        Node<K,V>[] newTable=new Node[DEFAULT_INITIAL_CAPACITY<<1];

        //2、重新计算index索引
        for (int i = 0; i < table.length; i++) {
            Node<K,V> oldNode=table[i];
            while (oldNode!=null){
                table[i]=null;  //赋值为null--------为垃圾回收机制能够回收
                K oldkey=oldNode.key;
                //重新计算index
                int index=getIndex(oldkey,newTable.length);
                Node<K,V> oldNext=oldNode.next;

                //如果index下标相同，存放链表;原来node的下一个是最新的（原来的node存放新的node的下一个）
                oldNode.next=newTable[index];

                //将之前的node赋值给newTable[index]
                newTable[index]=oldNode;
                //判断是否进行循环遍历
                oldNode=oldNext;
            }
        }
        //3、将新的table赋值
        table=newTable;
        DEFAULT_INITIAL_CAPACITY=newTable.length;
        newTable=null;  //赋值为null--------为垃圾回收机制能够回收
    }

    /**
     * 测试打印hashmap所有元素
     */
    public void print(){
        for (int i = 0; i < table.length; i++) {
            Node node=table[i];
            System.out.print("下标位置["+i+"]:");
            while (node!=null){
                System.out.print("[key:"+node.getKey()+",Value:"+node.getValue()+"]");
                if (node.next!=null){
                    node=node.next;
                }else {
                    node=null;
                }
            }
            System.out.println();
        }
    }

    public int getIndex(K key,int length){
        int hashCode=key.hashCode();
        int index=hashCode%length;
        return index;
    }

    @Override
    public V get(K key) {
        Node node=table[getIndex(key,DEFAULT_INITIAL_CAPACITY)];
        Node<K,V> node1=getNode(node,key);
        return node1==null?null:node1.value;
    }

    public Node getNode(Node<K,V> node,K key){
        while (node!=null){
            if (node.getKey().equals(key)){
                return node;
            }
            node=node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    /**
     * 定义节点
     */
    class Node<K,V> implements Entry<K,V>{

        private K key;

        private V value;

        //下一个节点
        private Node next;

        public Node(K key,V value,Node next){
            this.key=key;
            this.value=value;
            this.next=next;
        }


        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public V setValue(V value) {
            V oldValue=this.value;
            this.value=value;
            return oldValue;
        }
    }
}
