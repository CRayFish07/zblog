package zb.blog.controller;

import com.sun.org.apache.regexp.internal.RE;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zb.blog.BlogCfg;
import zb.blog.cache.StaticResCache;
import zb.blog.model.BlogMeta;
import zb.blog.model.BlogPage;
import zb.blog.service.BlogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    public String getIndex1(ModelMap model, HttpServletRequest req, HttpServletResponse rsp) {
        String ftlName = "blogftl/index1.html";
        model.put("cfg", blogCfg);
        return ftlName;
    }

    

    @GetMapping("/about.jsp")
    public String getAbout(ModelMap model,HttpServletRequest req, HttpServletResponse rsp) {
        String ftlName ="blogftl/about.html";
        model.put("cfg", blogCfg);
        return ftlName;
    }

    @GetMapping("/postfile.jsp")
    public String getPostFile(ModelMap model,HttpServletRequest req, HttpServletResponse rsp) {
        String ftlName ="blogftl/postfile.html";
        model.put("cfg", blogCfg);
        return ftlName;
    }

    @GetMapping("/postblog.jsp")
    public String getPostBlog(String uid,ModelMap model,HttpServletRequest req, HttpServletResponse rsp) {
        model.put("cfg", blogCfg);
        model.put("blogUid",uid);
        return "blogftl/postblog.html";
    }

    @GetMapping("/blogdetail.jsp")
    public String getAbout(String uid,ModelMap model,HttpServletRequest req, HttpServletResponse rsp) {
        if(uid==null)
            throw new RuntimeException("Uid cant be null.");

        String ftlName = "blogftl/blogdetail.html";
        model.put("cfg", blogCfg);
        model.put("blogUid",uid);
        return ftlName;
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
