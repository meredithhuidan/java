package cn.meredith.day19;

import cn.meredith.day18.service.UserService1;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test001 {

    public static void main(String[] args) {
        //SpringIOC Xml版本使用dom4j+反射机制
        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext("spring_02.xml");
        System.out.println("############################");
        UserService1 userService1 = (UserService1) classPathXmlApplicationContext.getBean("userService");
        System.out.println(userService1);
    }
}
