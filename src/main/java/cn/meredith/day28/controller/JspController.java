package cn.meredith.day28.controller;

import javafx.scene.chart.ValueAxis;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * springboot整合jsp
 * 打包时要求打包为war包，不可以打包为jar，否则页面显示不出来
 */
@Controller
public class JspController {

    @RequestMapping(value = "/jspIndex")
    public String jspIndex(){
        return "jspIndex";
    }
}
