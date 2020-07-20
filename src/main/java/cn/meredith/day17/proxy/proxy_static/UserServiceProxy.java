package cn.meredith.day17.proxy.proxy_static;

import cn.meredith.day17.service.UserService;

/**
 * 静态代理设计模式
 * 一般不推荐用
 * 因为都要写一个代理类对象，性能不好，不复用
 *
 * @author Meredith
 * @date
 */
public class UserServiceProxy {

    private UserService userService;

    public UserServiceProxy(UserService userService){
        this.userService=userService;
    }

    public void add(){
        System.out.println("静态代理--开启事务");
        userService.add();
        System.out.println("静态代理--提交事务");
    }
}
