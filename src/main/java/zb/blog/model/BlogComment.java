package zb.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by zhmt on 2017/5/26.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogComment {
    public String blogUid;
    public Long rowDt;
    public String author;
    public String ip;
    public Long dt;
    public boolean deleted;
    public Long updatedt;
    public String comment;

    public String getBlogUid() {
        return blogUid;
    }

    public Long getRowDt() {
        return rowDt;
    }

    public String getAuthor() {
        return author;
    }

    public String getIp() {
        return ip;
    }

    public Long getDt() {
        return dt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getUpdatedt() {
        return updatedt;
    }

    public String getComment() {
        return comment;
    }

    public String getDtStr() {
         return BlogMeta.formatDate(dt);
    }
}
