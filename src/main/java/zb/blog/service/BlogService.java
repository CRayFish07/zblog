package zb.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zb.blog.BlogCfg;
import zb.blog.dao.BlogCommentMapper;
import zb.blog.dao.BlogContentMapper;
import zb.blog.dao.BlogMetaMapper;
import zb.blog.model.*;
import zb.blog.util.ExceptionUtil;
import zb.blog.util.ThreadService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhmt on 2017/5/31.
 */
@Service
public class BlogService {
    @Autowired
    private BlogMetaMapper blogMetaMapper;

    @Autowired
    private BlogContentMapper blogContentMapper;

    @Autowired
    private BlogCfg blogCfg;

    @Autowired
    private ThreadService threadService;

    //新建博文无需排队
    @Transactional
    public String postBlog(String title, String author, String content) {
        long timenow = System.currentTimeMillis();
        BlogMeta meta = new BlogMeta();
        meta.title = title;
        meta.author = author;
        meta.dt = timenow;
        meta.updatedt = timenow;
        meta.uid = UUID.randomUUID().toString();
        BlogContent blogContent = new BlogContent();
        blogContent.content = content;
        blogContent.dt = timenow;
        blogContent.updatedt = timenow;
        blogContent.uid = meta.uid;
        blogMetaMapper.post(meta);
        blogContentMapper.post(blogContent);
        return meta.uid;
    }

    //获取列表，无需排队 1-xxx
    public List<BlogMeta> list(int pageNum) {
        return blogMetaMapper.getList((pageNum-1)*blogCfg.blogListPageSize,blogCfg.blogListPageSize);
    }

    public BlogPage getPage(Integer page) {
        BlogPage ret = new BlogPage();
        if(page==null)
            page = 1;
        else
            page = page<1?1:page;

        ret.pageCount = getBlogPageCount();
        if(ret.pageCount>0) {
            page = page > ret.pageCount ? ret.pageCount : page;
            List<BlogMeta> list = list(page);
            ret.list = new ArrayList<>();
            List<BlogMeta> tmp = null;
            for(int i=0; i<list.size(); i++) {
                if(i%2==0) {
                    if(tmp!=null) {
                        ret.list.add(tmp);
                        tmp = null;
                    }
                }
                if(tmp==null)
                    tmp = new ArrayList<>();
                tmp.add(list.get(i));
            }
            if(tmp!=null) {
                ret.list.add(tmp);
                tmp = null;
            }
        }
        ret.page = page;

        ret.last = ret.page<=1?ret.page:ret.page-1;
        ret.next = ret.page>=ret.pageCount?ret.page:ret.page+1;
        
        return  ret;
    }

    public int getBlogCount() {
        return blogMetaMapper.count();
    }

    /**
     * 博客总页数
     * @return
     */
    public int getBlogPageCount() {
        int count = getBlogCount();
        count = count/blogCfg.blogListPageSize + (count%blogCfg.blogListPageSize==0?0:1);
        return count;
    }

    public BlogMeta getBlogMeta(String uid) {
        return blogMetaMapper.get(uid);
    }

    public BlogContent getBlogContent(String uid) {
        return blogContentMapper.get(uid);
    }

    @Transactional
    public String putBlog(String uid, String title, String author, String content) {
        BlogContent blogContent = blogContentMapper.get(uid);
        BlogMeta meta = blogMetaMapper.get(uid);
        if(blogContent==null || meta==null)
            throw new RuntimeException(blogCfg.strBlogNotExists);
        long timenow = System.currentTimeMillis();
        meta.updatedt = timenow;
        meta.title = title;
        meta.author = author;
        blogContent.content = content;
        blogContent.updatedt = timenow;
        blogMetaMapper.put(meta);
        blogContentMapper.put(blogContent);
        return uid;
    }

    public Long getMetaUpdatedt(String uid) {
        return blogMetaMapper.getUpdatedt(uid);
    }


}
