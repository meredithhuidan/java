package cn.meredith.day19.service.impl;

import cn.meredith.day19.service.OrderService;
import cn.meredith.day19.spring.extannotation.ExtService;

@ExtService
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder() {
        System.out.println("addOrder");
    }
}
