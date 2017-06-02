package zb.blog.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import zb.blog.BlogCfg;
import zb.blog.dao.BlogContentMapper;
import zb.blog.dao.BlogMetaMapper;
import zb.blog.model.BlogComment;
import zb.blog.model.BlogCommentRow;
import zb.blog.model.BlogContent;
import zb.blog.model.BlogMeta;
import zb.blog.service.BlogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

/**
 * Created by zhmt on 2017/5/26.
 */
@RestController()
public class BlogController {
    protected static final Logger log = LogManager.getRootLogger();

    @Autowired
    private BlogService blogService;
    
    @Autowired
    private BlogCfg blogCfg;


    /**
     * 博客列表json
     * @param page
     * @return
     */
    @GetMapping("/blog/list")
    public List<BlogMeta> getBlogList(Integer page) {
        if(page==null)
            page = 1;
        return blogService.list(page);
    }

   

    /**
     * 发布博客
     * @param title
     * @param author
     * @param password
     * @param content
     * @return
     */
    @PostMapping("/blog")
    public String postBlog(String title,String author,String password,String content) {
        if(StringUtils.isBlank(title) || title.length()>blogCfg.maxTitleLen) {
            throw new RuntimeException( blogCfg.getStrTitleLimit());
        }
        if(StringUtils.isBlank(author) || title.length()>blogCfg.maxAuthorLen) {
            throw new RuntimeException( blogCfg.getStrAuthorLimit());
        }
//        if(StringUtils.isBlank(password) || title.length()>blogCfg.maxPasswordLen) {
//            return blogCfg.getStrPasswordLimit();
//        }
        if(StringUtils.isBlank(content) || title.length()>blogCfg.maxContentLen) {
            throw new RuntimeException( blogCfg.getStrContentLimit());
        }

        blogService.postBlog(title,author,password,content);
        return "OK";
    }

    @ExceptionHandler(RuntimeException.class)
    public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        //If exception has a ResponseStatus annotation then use its response code
        ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        HttpStatus status =  (responseStatusAnnotation != null ? responseStatusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR);
        if(ex!=null)
            log.error("",ex);
        response.sendError(status.value(),ex==null?"null":ex.getMessage());
    }

    @PutMapping("/blog/meta")
    public String putBlogMeta(BlogMeta blogMeta) {
        return UUID.randomUUID().toString();
    }

    @GetMapping("/blog/meta")
    public String getBlogMeta(String uid) {
        return UUID.randomUUID().toString();
    }

    @PutMapping("/blog/content")
    public String putBlogContent(BlogCommentRow content) {
        return UUID.randomUUID().toString();
    }
    
    @GetMapping("/blog/content")
    public String getBlogContent(String uid) {
        return UUID.randomUUID().toString();
    }

    @PostMapping("/blog/comment")
    public String postBlogComment(BlogComment comment) {
        return UUID.randomUUID().toString();
    }

    @PutMapping("/blog/comment")
    public String putBlogComment(BlogComment comment) {
        return UUID.randomUUID().toString();
    }

    @GetMapping("/blog/comment")
    public String getBlogComment(String uid,Integer page) {
        return UUID.randomUUID().toString();
    }
}
