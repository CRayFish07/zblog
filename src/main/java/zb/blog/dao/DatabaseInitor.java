package zb.blog.dao;

import org.apache.ibatis.annotations.Update;

/**
 * 如果当前记录发生变化，要修改updatedt,用updatedt作为eTag字段，判断是否更改   .
 *
 * 字段名尽量与javabean属性名一致，比如属性名位userId，那么字段名也取userId，而不user_id.不然，mybatis做字段映射时比较麻烦。
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

    @Update("CREATE TABLE IF NOT EXISTS blog_comment" +
            "(blog_uid VARCHAR(36) ," +
            "dt BIGINT NOT NULL, " +
            "comment_count INT NOT NULL,"+
            "content CLOB(1M))")
    void createTableComment();

    //数据库升级，把uid改为blog_uid
    @Update("ALTER TABLE blog_comment ALTER COLUMN uid RENAME TO blog_uid")
    void renameCommentUidToBlogUid();

    //数据库升级，废除索引 comment_uid_dt，启用 comment_bloguid_dt
    @Update("DROP INDEX IF EXISTS comment_uid_dt")
    void dropTableCommentIndexUidSeq();
    @Update("CREATE INDEX comment_bloguid_dt ON blog_comment(blog_uid,dt)")
    void createTableCommentIndexUidSeq();

    @Update("CREATE TABLE IF NOT EXISTS blog_homeabout (uid VARCHAR(36) PRIMARY KEY , content VARCHAR(36))")
    void createTableHomeAndAbout();
}
