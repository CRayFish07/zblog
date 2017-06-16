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
import zb.blog.model.CommentPage;
import zb.blog.util.ExceptionUtil;
import zb.blog.util.ThreadService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhmt on 2017/6/13.
 */
@Service
public class CommentService {
    public static final int ROWS_PER_PAGE = 2;
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
            BlogComment blogComment = newBlogComment(blogUid,author,comment,ip,row);

            List<BlogComment> commentList = deserialComment(row);
            //保证列表前面的是最新评论
            commentList.add(0,blogComment);
            row.commentCount = commentList.size();
            row.content = serialComment(commentList);

            if(isNewRow)
                blogCommentMapper.post(row);
            else
                blogCommentMapper.put(row);
        });
    }
    
    public int deleteByBlogUid(String blogUid) {
        return blogCommentMapper.deleteByBlogUid(blogUid);
    }

    public int deleteComment(String blogUid,long rowDt,String author,long dt) {
        return threadService.exeAndWait(blogUid,()->{
            BlogCommentRow row = blogCommentMapper.getRow(blogUid,rowDt);
            if(row==null)
                return 0;

            List<BlogComment> commentList = deserialComment(row);
            for(BlogComment one : commentList) {
                if(author.equals(one.author) && dt==one.dt) {
                    one.deleted = true;
                    row.content = serialComment(commentList);
                    blogCommentMapper.put(row);
                    return 1;
                }
            }

            return 0;
        });
    }

    public String getCommentEtag(String blogUid, int page) {
        List<BlogCommentRow> rows = blogCommentMapper.getPageEtag(blogUid,(page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE);
         if(rows==null)
             return null;
         String ret = "";
         for(BlogCommentRow row:rows) {
            ret += row.dt+"."+row.commentCount;
         }
         return Integer.toString(ret.hashCode());
    }

    /**
     * 获取评论json
     * @param blogUid
     * @param page
     * @return
     */
    public CommentPage getCommentJson(String blogUid, int page) {
        if(page<1)
            page = 1;
        int pageCount = getCommentPageCount(blogUid);
        if(pageCount>0 && page>pageCount) {
            page = pageCount;
        }

        List<BlogCommentRow> rows = blogCommentMapper.get(blogUid,(page-1)*ROWS_PER_PAGE,ROWS_PER_PAGE);
        if(rows==null || rows.size()<=0)
            return null;

        CommentPage ret = new CommentPage();
        ret.page = page;
        ret.pageCount = pageCount;
        ret.last = ret.page<=1?ret.page:ret.page-1;
        ret.next = ret.page>=ret.pageCount?ret.page:ret.page+1;
        ret.list = new LinkedList<>();
        for(BlogCommentRow row : rows) {
            ret.list.addAll(deserialComment(row));
        }
        for (BlogComment one : ret.list) {
            if(one.deleted)
                one.comment = blogCfg.strCommentDeleted;
        }

        return ret;
    }

    /**
     * 获取评论页数,两条记录算作一页
     * @return
     */
    public int getCommentPageCount(String blogUid) {
        Integer ret = blogCommentMapper.countForOneBlog(blogUid);
        if (ret == null)
            return 0;
        ret = ret / ROWS_PER_PAGE + (ret % ROWS_PER_PAGE > 0 ? 1 : 0);
        return ret;
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

    private BlogComment newBlogComment(String blogUid, String author, String comment,String ip,BlogCommentRow row) {
        BlogComment blogComment = new BlogComment();
        blogComment.author = author;
        blogComment.blogUid = blogUid;
        blogComment.comment = comment;
        blogComment.deleted = false;
        blogComment.dt = System.currentTimeMillis();
        blogComment.ip = ip;
        blogComment.rowDt = row.dt;
        blogComment.updatedt = blogComment.dt;
        return blogComment;
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
