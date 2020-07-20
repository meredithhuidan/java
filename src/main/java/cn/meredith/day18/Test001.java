package cn.meredith.day18;


import cn.meredith.day18.service.UserService1;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 注解版本声明事务
 *
 * @author Meredith
 * @date
 */
public class Test001 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
        UserService1 userService1 = (UserService1) applicationContext.getBean("userServiceImpl1");
        userService1.add();
    }
}
