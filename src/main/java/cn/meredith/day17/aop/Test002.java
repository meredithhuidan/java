package cn.meredith.day17.aop;

import cn.meredith.day17.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test002 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        UserService userService= (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
