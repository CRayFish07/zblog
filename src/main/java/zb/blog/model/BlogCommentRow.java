package zb.blog.model;

/**
 * Created by zhmt on 2017/5/26.
 */
public class BlogCommentRow {
    public String blogUid;  //blogid;
    public Long dt;
    //当前保存了几条评论
    public Integer commentCount;
    public String content;

    public String getBlogUid() {
        return blogUid;
    }

    public Long getDt() {
        return dt;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public String getContent() {
        return content;
    }
}
