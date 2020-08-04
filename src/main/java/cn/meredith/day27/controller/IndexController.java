package cn.meredith.day27.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个springboot项目
 * 微服务情况下，基本都在类上加上@RestController
 * 启动项目 方式一
 *
 * @author Meredith
 * @date
 */
//@Controller
@RestController
@EnableAutoConfiguration
public class IndexController {

    //@RestController修饰的类下的所有方法，全部都是返回json格式，
    // 这样的话不用在方法上加上@ResponseBody
    //@ResponseBody
    @RequestMapping(value = "index")
    public String index(){
        return "SpringBoot";
    }

    //思考：如何启动
    //@EnableAutoConfiguration    //作用在于让SpringBoot根据应用所声明的依赖来对Spring框架进行自动配置
    public static void main(String[] args) {
        //springboot程序入口,默认端口号8080
        SpringApplication.run(IndexController.class,args);
    }
}
