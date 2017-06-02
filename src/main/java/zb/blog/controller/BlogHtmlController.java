package zb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 输出div：博客列表和分页导航
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/blog/listhtml")
    public String getBlogListHtml(Integer page,ModelMap model) {
        BlogPage blogPage = blogService.getPage(page);
        
        model.put("cfg", blogCfg);
        model.put("blogPage", blogPage);
        return "blogftl/comm/list.content.html";
    }
}
