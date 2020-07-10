package cn.meredith.day08.entity;

/**
 * 声明一个event:生产者与消费者传递的数据类型
 *
 * @author Meredith
 * @date
 */
public class LongEvent {

    private Long value;

    public void setValue(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
