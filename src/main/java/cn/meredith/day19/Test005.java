package cn.meredith.day19;

import cn.meredith.day17.service.UserService;
import cn.meredith.day19.service.UserService3;
import cn.meredith.day19.spring.ext.ExtClassPathXmlApplicationContext1;

public class Test005 {

    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext1 app=new ExtClassPathXmlApplicationContext1("cn.meredith.day19.service.impl");
        UserService3 userService= (UserService3) app.getBean("userService3Impl3");
        userService.add();
        System.out.println(userService);
    }
}
