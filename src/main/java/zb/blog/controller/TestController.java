package zb.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by zhmt on 2017/5/25.
 */
@Controller()
public class TestController {
    @RequestMapping("hello")
    public String welcome(ModelMap model) {
        model.put("time", new Date());
        model.put("message", "sfsdfsd");
        System.out.println("sdfsdfsdf---=====");
        return "hello";
    }
}
