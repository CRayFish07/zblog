package zb.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zb.blog.BlogCfg;
import zb.blog.model.BlogMeta;
import zb.blog.model.BlogPage;
import zb.blog.service.BlogService;

import java.util.Date;
import java.util.List;

/**
 * Created by zhmt on 2017/6/1.
 */
@Controller()
public class BlogHtmlController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private BlogCfg blogCfg;

    @GetMapping("/index1.jsp")
    public String getIndex1(ModelMap model) {
        model.put("cfg", blogCfg);
        return "blogftl/index1.html";
    }

    @GetMapping("/about.jsp")
    public String getAbout(ModelMap model) {
        model.put("cfg", blogCfg);
        return "blogftl/about.html";
    }

    @GetMapping("/postfile.jsp")
    public String getPostFile(ModelMap model) {
        model.put("cfg", blogCfg);
        return "blogftl/postfile.html";
    }

    @GetMapping("/postblog.jsp")
    public String getPostBlog(String uid,ModelMap model) {
        model.put("cfg", blogCfg);
        model.put("blogUid",uid);
        return "blogftl/postblog.html";
    }

    @GetMapping("/blogdetail.jsp")
    public String getAbout(String uid,ModelMap model) {
        if(uid==null)
            throw new RuntimeException("Uid cant be null.");
        model.put("cfg", blogCfg);
        model.put("blogUid",uid);
        return "blogftl/blogdetail.html";
    }

    @GetMapping("/bloglist.jsp")
    public String getBloglist(Integer page, ModelMap model) {
        if(page==null)
            page = 1;
        BlogPage blogPage = blogService.getPage(page);

        model.put("cfg", blogCfg);
        model.put("blogPage", blogPage);
        return  "blogftl/bloglist.html";
    }
}
