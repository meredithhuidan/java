package cn.meredith.day11.adaptor;

/**
 * 电源适配器
 */
public class PowerAdaptor implements JP110VInterface{

    private CN220VInterface cn220VInterface;

    public PowerAdaptor(CN220VInterface cn220VInterface){
        this.cn220VInterface=cn220VInterface;
    }

    public void connect() {
        cn220VInterface.connect();
    }
}
