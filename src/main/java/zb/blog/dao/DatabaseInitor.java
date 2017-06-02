package zb.blog.dao;

import org.apache.ibatis.annotations.Update;

/**
 * 如果当前记录发生变化，要修改updatedt,用updatedt作为eTag字段，判断是否更改
 * Created by zhmt on 2017/5/26.
 *
 */
public interface DatabaseInitor {
    @Update("SET DATABASE DEFAULT TABLE TYPE CACHED")
    void setDefaultTableType();

    @Update("CREATE TABLE IF NOT EXISTS blog_meta" +
            "(uid VARCHAR(36) PRIMARY KEY ," +
            "dt BIGINT NOT NULL," +
            "updatedt BIGINT NOT NULL," +
            "author VARCHAR(64) NOT NULL, " +
            "title VARCHAR(128) NOT NULL) ")
    void createTableBlogMeta();

    @Update("CREATE INDEX blog_meta_updatedt ON blog_meta(updatedt)")
    void createBlogMetaIndexUpdatedt();

    @Update("CREATE TABLE IF NOT EXISTS blog_content" +
            "(uid VARCHAR(36) PRIMARY KEY ," +
            "updatedt BIGINT NOT NULL,"+
            "content CLOB(1M))")
    void createTableBlogContent();

    //每条记录存储20条评论
    @Update("CREATE TABLE IF NOT EXISTS blog_comment" +
            "(uid VARCHAR(36) ," +
            "dt BIGINT NOT NULL, " +
            "comment_count INT NOT NULL,"+
            "content CLOB(1M))")
    void createTableComment();

    @Update("CREATE INDEX comment_uid_dt ON blog_comment(uid,dt)")
    void createTableCommentIndexUidSeq() ;
}
