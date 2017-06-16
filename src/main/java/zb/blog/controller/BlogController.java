package zb.blog.controller;

import org.apache.catalina.loader.Constants;
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
import zb.blog.cache.StaticResCache;
import zb.blog.dao.BlogContentMapper;
import zb.blog.dao.BlogMetaMapper;
import zb.blog.model.*;
import zb.blog.security.LoginRequired;
import zb.blog.service.BlogService;
import zb.blog.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * Created by zhmt on 2017/5/26.
 */
@RestController()
public class BlogController {
    protected static final Logger log = LogManager.getRootLogger();

    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;
    
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
     * 发表博文
     * @param title
     * @param author
     * @param content
     * @return
     */
    @LoginRequired 
    @PostMapping("/blog")
    public String postBlog(String uid,String title,String author,String content) {
        
        if(StringUtils.isBlank(title) || title.length()>blogCfg.maxTitleLen) {
            throw new RuntimeException( blogCfg.getStrTitleLimit());
        }
        if(StringUtils.isBlank(author) || author.length()>blogCfg.maxAuthorLen) {
            throw new RuntimeException( blogCfg.getStrAuthorLimit());
        }

        if(StringUtils.isBlank(content) || title.length()>blogCfg.maxContentLen) {
            throw new RuntimeException( blogCfg.getStrContentLimit());
        }

        if(StringUtils.isBlank(uid))
            return blogService.postBlog(title,author,content);
        else
            return blogService.putBlog(uid,title,author,content);
    }

    @LoginRequired
    @PostMapping("/blog/delete")
    public void deleteBlog(String uid) {
        if(StringUtils.isBlank(uid)) {
            throw  new RuntimeException("uid cant be null");
        }
        blogService.deleteBlog(uid);
        int deleted2 = commentService.deleteByBlogUid(uid);
        return;
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


    @GetMapping("/blog/meta")
    public BlogMeta getBlogMeta(String uid) {
        return blogService.getBlogMeta(uid);
    }
    
    
    @GetMapping("/blog/content")
    public BlogContent getBlogContent(String uid) {
        return blogService.getBlogContent(uid);
    }

    @PostMapping("/blog/comment")
    public void postBlogComment(String blogUid,String commentor,String comment,String robotCheckCode,HttpServletRequest request) {
        if(StringUtils.isBlank(robotCheckCode) ||
                !robotCheckCode.equalsIgnoreCase((String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY)) ) {
            throw new RuntimeException(blogCfg.strInvalidRobotCheckCode);
        }
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        if(StringUtils.isBlank(blogUid)) {
            throw new RuntimeException("blogUid cant be null.");
        }
        if(StringUtils.isBlank(commentor) || commentor.length()>blogCfg.maxAuthorLen)  {
            throw  new RuntimeException(blogCfg.getStrCommentorLimit());
        }
        if(StringUtils.isBlank(comment) || comment.length()>blogCfg.maxCommentLen)  {
            throw  new RuntimeException(blogCfg.getStrCommentLimit());
        }
        commentService.postComment(blogUid,commentor,comment,request.getRemoteHost());
    }


    @GetMapping("/blog/comment")
    public CommentPage getBlogComment(String blogUid, Integer page,HttpServletRequest req, HttpServletResponse rsp) {
        if(blogUid==null)
            throw new RuntimeException("blogUid cant be null.");
        if(page==null)
             page = 1;

        String curEtag = commentService.getCommentEtag(blogUid,page);
        if(StaticResCache.processEtag(req,rsp,curEtag,1)) {
            return  null;
        }
        
        return commentService.getCommentJson(blogUid,page);
    }

    @LoginRequired
    @PostMapping("/blog/comment/delete")
    public void deleteComment(String blogUid,Long rowDt,String author,Long dt) {
        if(StringUtils.isBlank(blogUid)) {
            throw new RuntimeException("blogUid cant be null");
        }
        if(rowDt==null) {
            throw new RuntimeException("rawDt cant be null");
        }
        if(StringUtils.isBlank(author)) {
            throw new RuntimeException("author cant be null");
        }
        if(dt==null) {
            throw new RuntimeException("dt cant be null");
        }

        commentService.deleteComment(blogUid,rowDt,author,dt);
    }
}
