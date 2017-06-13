package zb.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.BlogCfg;
import zb.blog.dao.BlogCommentMapper;
import zb.blog.dao.BlogMetaMapper;
import zb.blog.model.BlogComment;
import zb.blog.model.BlogCommentRow;
import zb.blog.util.ExceptionUtil;
import zb.blog.util.ThreadService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhmt on 2017/6/13.
 */
@Service
public class CommentService {
    @Autowired
    private BlogMetaMapper blogMetaMapper;

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Autowired
    private BlogCfg blogCfg;

    @Autowired
    private ThreadService threadService;
    
    /**
     * 发表评论
     * 这个方法必须排队，因为评论是并发的。
     * @param blogUid
     * @param author
     * @param comment
     */
    public void postComment(String blogUid, String author, String comment,String ip) {
        threadService.exeAndWait(blogUid,()->{
            if(blogMetaMapper.get(blogUid)==null)
                return ;

            BlogCommentRow row = blogCommentMapper.getFreeRow(blogUid);
            boolean isNewRow = false;
            if(row==null) {
                row = newCommentRow(blogUid);
                isNewRow = true;
            }
            BlogComment blogComment = new BlogComment();
            blogComment.author = author;
            blogComment.blogUid = blogUid;
            blogComment.comment = comment;
            blogComment.deleted = false;
            blogComment.dt = System.currentTimeMillis();
            blogComment.ip = ip;
            blogComment.rowDt = row.dt;
            blogComment.updatedt = blogComment.dt;

            List<BlogComment> commentList = deserialComment(row);
            commentList.add(blogComment);
            row.commentCount = commentList.size();
            row.content = serialComment(commentList);

            if(isNewRow)
                blogCommentMapper.post(row);
            else
                blogCommentMapper.put(row);
        });
    }

    public String getCommentEtag(String blogUid, int page) {
         BlogCommentRow row = blogCommentMapper.getPageEtag(blogUid,page-1);
         if(row==null)
             return null;
         return row.dt+"."+row.commentCount;
    }

    /**
     * 获取评论json
     * @param blogUid
     * @param page
     * @return
     */
    public String getCommentJson(String blogUid, int page) {
        return blogCommentMapper.get(blogUid,page-1).getContent();
    }

    /**
     * 获取评论页数
     * @return
     */
    public int getCommentPageCount(String blogUid) {
        return blogCommentMapper.countForOneBlog(blogUid);
    }

    /**
     * 反序列化评论
     * @param row
     * @return
     */
    private List<BlogComment> deserialComment(BlogCommentRow row) {
        if(StringUtils.isBlank(row.content)) {
            return new LinkedList<>();
        }
        List<BlogComment> ret = new LinkedList<>();
        ObjectMapper m = new ObjectMapper();
        List<BlogComment> list = ExceptionUtil.castException(()->{
            return m.readValue(row.content, TypeFactory.defaultInstance().constructCollectionType(List.class, BlogComment.class));
        });
        return  list;
    }

    /**
     * 序列化评论
     * @param list
     * @return
     */
    private String serialComment(List<BlogComment> list) {
        ObjectMapper m = new ObjectMapper();
        String str = ExceptionUtil.castException(()->{return m.writeValueAsString(list);});
        return  str;
    }

    /**
     * 创建并初始化一个评论行
     * @param blogUid
     * @return
     */
    private BlogCommentRow newCommentRow(String blogUid) {
        BlogCommentRow row = new BlogCommentRow();
        row.blogUid = blogUid;
        row.dt = System.currentTimeMillis();
        return row;
    }
}
