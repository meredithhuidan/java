package cn.meredith.day17;

import cn.meredith.day17.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test002 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        UserService userService= (UserService) applicationContext.getBean("userServiceImpl");
        userService.add();
    }
}
