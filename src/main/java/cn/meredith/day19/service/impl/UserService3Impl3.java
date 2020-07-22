package cn.meredith.day19.service.impl;

import cn.meredith.day19.service.OrderService;
import cn.meredith.day19.service.UserService3;
import cn.meredith.day19.spring.extannotation.ExtResource;
import cn.meredith.day19.spring.extannotation.ExtService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * user service层
 */
@ExtService
public class UserService3Impl3 implements UserService3 {

    @ExtResource
    private OrderService orderServiceImpl;

    @Override
    public void add() {
        orderServiceImpl.addOrder();
        System.out.println("使用java反射机制初始化对象");
    }
}
