package cn.meredith.day08.producer;

import cn.meredith.day08.consumer.LongEventHandler;
import cn.meredith.day08.consumer.LongEventHandler2;
import cn.meredith.day08.entity.LongEvent;
import cn.meredith.day08.factory.LongEventFactory;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * disruptor
 * 单个生产者，单个消费者
 * 单个生产者，多个消费者
 *
 * @author Meredith
 * @date
 */
public class Main {

    public static void main(String[] args) {
        //1、创建一个可缓存的线程池，提供发给consumer
        ExecutorService executor = Executors.newCachedThreadPool();
        //2、创建工厂
        EventFactory<LongEvent> eventFactory = new LongEventFactory();
        //3、创建ringBuffer大小
        int ringBufferSize = 1024 * 1024;   //2的n次方
        //4、创建disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, ringBufferSize, executor, ProducerType.MULTI, new YieldingWaitStrategy());
        //5、连接消费者/注册消费者
        disruptor.handleEventsWith(new LongEventHandler());
        //多个消费者，一个生产者，默认重复消费，否则可以配置分组
        disruptor.handleEventsWith(new LongEventHandler2());
        //6、启动disruptor
        disruptor.start();
        //7、创建RingBuffer容器
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        //8、创建生产者
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        //9、指定缓冲区大小
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 1; i < 100; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }
        executor.shutdown();
        disruptor.shutdown();
    }
}
