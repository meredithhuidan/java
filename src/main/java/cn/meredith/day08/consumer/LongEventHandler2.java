package cn.meredith.day08.consumer;

import cn.meredith.day08.entity.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * 消费者2
 *
 * @author Meredith
 * @date
 */
public class LongEventHandler2 implements EventHandler<LongEvent> {

    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("消费者2获取生产者数据...event:" + event.getValue());
    }
}
