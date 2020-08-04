package cn.meredith.day28.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 整合freemarker
 */
@Controller
public class FTLIndexController {

//    @ResponseBody  //加这个注解。只能返回字符串；要跳转页面不能加这个注解
    @RequestMapping(value = "/ftlIndex")
    public String ftlIndex(Map<String,Object> map){
        map.put("name","薛白");
        map.put("age",18);
        map.put("gender","1");
        return "ftlIndex";
    }
}
