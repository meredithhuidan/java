package cn.meredith.day27;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动springboot项目
 * 第二种方式
 * 第三种方式
 *
 * @author Meredith
 * @date
 */
//@ComponentScan("cn.meredith.day27.controller")
//@EnableAutoConfiguration
    @SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
