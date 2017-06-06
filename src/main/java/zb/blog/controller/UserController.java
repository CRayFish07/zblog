package zb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.blog.BlogCfg;
import zb.blog.service.SessionMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhmt on 2017/6/5.
 */
@RestController
public class UserController {
    @Autowired
    private BlogCfg blogCfg;

    @Autowired
    private SessionMap sessionMap;

    @PostMapping("/login")
    public String login(String password, HttpServletRequest request, HttpServletResponse response) {
        if(blogCfg.pwd.equals(password)) {
            //request.getSession().setAttribute("login",true);
            //request.getSession().setMaxInactiveInterval(15*60);
           SessionMap.Session session = sessionMap.newSession();
           return session.getSid();
        }
        throw new RuntimeException(blogCfg.strLoginFailed);
    }
}
