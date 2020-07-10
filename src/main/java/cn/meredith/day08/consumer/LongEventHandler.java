package cn.meredith.day08.consumer;

import cn.meredith.day08.entity.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * disruptor消费者不需要取数据
 * 消费者获取生产推送数据
 * <p>
 * 事件消费者，也就是一个事件处理器。这个事件处理器简单地把事件中存储的数据打印到终端
 *
 * @author Meredith
 * @date
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("消费者1获取生产者数据...event:" + event.getValue());
    }
}
