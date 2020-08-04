package cn.meredith.day27.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberController {

    @RequestMapping(value = "/getMember",produces = "application/json;charset=UTF-8")
    public Map getMember(){
        Map<String ,Object> hashMap=new HashMap<>();
        hashMap.put("errorCode",200);
        hashMap.put("errorMsg","请求失败");
        return hashMap;
    }
}
