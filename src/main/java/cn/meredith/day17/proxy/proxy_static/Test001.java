package cn.meredith.day17.proxy.proxy_static;

import cn.meredith.day17.service.UserService;
import cn.meredith.day17.service.impl.UserServiceImpl;

public class Test001 {

    public static void main(String[] args) {
        UserService userService=new UserServiceImpl();
//        userService.add();

        UserServiceProxy userServiceProxy=new UserServiceProxy(userService);
        userServiceProxy.add();
    }
}
