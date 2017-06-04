package zb.blog.model;

/**
 * Created by zhmt on 2017/5/31.
 */
public class BlogContent {
    public String uid;  //blogid
    public Long dt;
    public Long updatedt;
    public String content;

    public String getUid() {
        return uid;
    }

    public Long getDt() {
        return dt;
    }

    public Long getUpdatedt() {
        return updatedt;
    }

    public String getContent() {
        return content;
    }
}
