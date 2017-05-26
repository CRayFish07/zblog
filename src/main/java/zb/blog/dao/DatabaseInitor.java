package zb.blog.dao;

import org.apache.ibatis.annotations.Update;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface DatabaseInitor {
    @Update("SET DATABASE DEFAULT TABLE TYPE CACHED")
    void setDefaultTableType();

    @Update("CREATE TABLE IF NOT EXISTS blog_meta" +
            "(uid VARCHAR(36),dt BIGINT,updatedt BIGINT,author VARCHAR(64), title VARCHAR(128))")
    void createTableBlogMeta();

    @Update("CREATE TABLE IF NOT EXISTS blog_content" +
            "(uid VARCHAR(36), content CLOB(128))")
    void createTableBlogContent();

    @Update("CREATE TABLE IF NOT EXISTS blog_content" +
            "(uid VARCHAR(36), content CLOB(128))")
    void createTableComments();
}
