package cn.meredith.day08.producer;

import cn.meredith.day08.entity.LongEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 生产者
 *
 * @author Meredith
 * @date
 */
public class LongEventProducer {

    private RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    //ByteBuffer
    public void onData(ByteBuffer byteBuffer) {
        //获取事件队列下标位置
        long sequence = ringBuffer.next();
        try {

            //取出空队列（Event）
            LongEvent longEvent = ringBuffer.get(sequence);
            //给空队列赋值
            longEvent.setValue(byteBuffer.getLong(0));
        } catch (Exception e) {

        } finally {
            System.out.println("生产者发送数据...");
            //发送数据
            ringBuffer.publish(sequence);
        }
    }
}
