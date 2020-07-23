package cn.meredith.day20.springmvc.controller;

import cn.meredith.day20.springmvc.annotation.ExtController;
import cn.meredith.day20.springmvc.annotation.ExtRequestMapping;

@ExtController
@ExtRequestMapping("/ext")
public class ExtIndexController {

    @ExtRequestMapping("/test")
    public String test(){
        System.out.println("手写springmvc框架...");
        return "index";
    }
}
