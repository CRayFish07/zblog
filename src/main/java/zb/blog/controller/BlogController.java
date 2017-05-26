package zb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zb.blog.model.BlogComment;
import zb.blog.model.BlogContent;
import zb.blog.model.BlogMeta;
import zb.blog.service.DbInitService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhmt on 2017/5/26.
 */
@RestController()
public class BlogController {
    //@Autowired
    //private DbInitService dbInitService;

    @GetMapping("/blog/list")
    public String getBlogList(Integer page) {
        return UUID.randomUUID().toString();
    }

    @PostMapping("/blog")
    public String postBlog() {
        return UUID.randomUUID().toString();
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
    public String putBlogContent(BlogContent content) {
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
