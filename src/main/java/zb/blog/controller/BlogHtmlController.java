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

    @Autowired
    private freemarker.template.Configuration freeMarkerConfiguration;

    private static final int LONG_TERM_CACHE_TIME = 3600*24*30;

    @GetMapping("/index1.jsp")
    public String getIndex1(ModelMap model, HttpServletRequest req, HttpServletResponse rsp) {
        String ftlName = "blogftl/index1.html";
        String curEtag = Long.toString(getFtlLastModifyTime(ftlName));
        if(StaticResCache.processEtag(req,rsp,curEtag,LONG_TERM_CACHE_TIME)) {
            return  null;
        }
        model.put("cfg", blogCfg);
        return ftlName;
    }

    private static long getFtlLastModifyTime(String ftlName) {
        File f = new File(BlogHtmlController.class.getResource("/templates/"+ftlName+".ftl").getFile());
        return f.lastModified();
    }

    @GetMapping("/about.jsp")
    public String getAbout(ModelMap model,HttpServletRequest req, HttpServletResponse rsp) {
        String ftlName ="blogftl/about.html";
        String curEtag = Long.toString(getFtlLastModifyTime(ftlName));
        if(StaticResCache.processEtag(req,rsp,curEtag,LONG_TERM_CACHE_TIME)) {
            return  null;
        }
        model.put("cfg", blogCfg);
        return ftlName;
    }

    @GetMapping("/postfile.jsp")
    public String getPostFile(ModelMap model,HttpServletRequest req, HttpServletResponse rsp) {
        String ftlName ="blogftl/postfile.html";
        String curEtag = Long.toString(getFtlLastModifyTime(ftlName));
        if(StaticResCache.processEtag(req,rsp,curEtag,LONG_TERM_CACHE_TIME)) {
            return  null;
        }

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
        Long dt = blogService.getMetaUpdatedt(uid);
        Long ftlDt = getFtlLastModifyTime(ftlName);
        String curEtag = Integer.toString((dt+""+ftlDt).hashCode());
        if(StaticResCache.processEtag(req,rsp,curEtag,LONG_TERM_CACHE_TIME)) {
            return null;
        }

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
