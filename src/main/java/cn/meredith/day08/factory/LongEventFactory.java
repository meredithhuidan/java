package cn.meredith.day08.factory;

import cn.meredith.day08.entity.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * EventFactory实例化LongEvent
 *
 * @author Meredith
 * @date
 */
public class LongEventFactory implements EventFactory<LongEvent> {


    public LongEvent newInstance() {
        return new LongEvent();
    }
}
