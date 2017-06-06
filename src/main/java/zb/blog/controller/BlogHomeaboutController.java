package zb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.blog.BlogCfg;
import zb.blog.security.LoginRequired;
import zb.blog.service.BlogHomeaboutService;

/**
 * Created by zhmt on 2017/6/4.
 */
@RestController
public class BlogHomeaboutController {
    @Autowired
    private BlogHomeaboutService blogHomeaboutService;

    @Autowired
    private BlogCfg blogCfg;

    @LoginRequired
    @PostMapping("/setting/home")
    public void setHome(String blogUid){
        blogHomeaboutService.setHome(blogUid);
    }
    
    @GetMapping("/setting/home")
    public String getHome() {
        String ret = blogHomeaboutService.getHome();
        return ret==null?"":ret;
    }

    @LoginRequired
    @PostMapping("/setting/about")
    public void setAbout(String blogUid) {
        blogHomeaboutService.setAbout(blogUid);
    }

    @GetMapping("/setting/about")
    public String getAbout() {
        String ret = blogHomeaboutService.getAbout();
        return ret == null?"":ret;
    }
}
