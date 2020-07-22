package cn.meredith.day19;

import cn.meredith.day19.service.UserService3;
import cn.meredith.day19.spring.ext.ExtClassPathXmlApplicationContext;

/**
 * 测试手写springIOC代码
 *
 */
public class Test003 {

    public static void main(String[] args) throws Exception {
        ExtClassPathXmlApplicationContext app=new ExtClassPathXmlApplicationContext("spring_02.xml");
        UserService3 bean = (UserService3) app.getBean("userService3");
        bean.add();
    }
}
