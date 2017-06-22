package zb.blog.dao;

import org.apache.ibatis.annotations.*;
import zb.blog.model.BlogCommentRow;

import java.util.List;

/**
 * Created by zhmt on 2017/5/26.
 */

public interface BlogCommentMapper {
    /**
     * 每条数据库记录，存储10条评论，博客部署之后，不要修改这个数字
     */
    static final int COMMENTS_PER_ROW = 10;

    @Update("INSERT INTO blog_comment (blog_uid,dt,comment_count,content) VALUES(#{blogUid},#{dt},#{commentCount},#{content})")
    public int post(BlogCommentRow content);


    @Update("UPDATE blog_comment SET comment_count=#{commentCount},content=#{content} WHERE blog_uid=#{blogUid} AND dt=#{dt}")
    int put(BlogCommentRow content);

    //page 从0开始
    @Results(value = {
            @Result(id = true, property = "blogUid", column = "blog_uid"),
            @Result(property = "commentCount", column = "comment_count")
    })
    @Select("SELECT * FROM blog_comment WHERE blog_uid=#{blogUid} ORDER BY dt DESC LIMIT #{offset},#{rowCount} ")
    List<BlogCommentRow> get(@Param("blogUid") String blogUid, @Param("offset") int offset, @Param("rowCount") int rowCount);

    @Results(value = {
            @Result(id = true, property = "blogUid", column = "blog_uid"),
            @Result(property = "commentCount", column = "comment_count")
    })
    @Select("SELECT dt,comment_count FROM blog_comment WHERE blog_uid=#{blogUid} ORDER BY dt DESC LIMIT #{offset},#{rowCount} ")
    List<BlogCommentRow> getPageEtag(@Param("blogUid") String blogUid, @Param("offset") int offset, @Param("rowCount") int rowCount);

    //统计一篇博客有几页评论
    @Select("SELECT COUNT(blog_uid,dt) FROM blog_comment WHERE blog_uid=#{blogUid}")
    int countForOneBlog(String blogUid);

    /**
     * 一篇博客共有多少评论
     * @param blogUid
     * @return
     */
    @Select("SELECT SUM(comment_count) FROM blog_comment WHERE blog_uid=#{blogUid}")
    int countReplyForOneBlog(String blogUid);

    @Select("<script> SELECT blog_uid AS blogUid ,SUM(comment_count) AS commentCount " +
            "FROM blog_comment WHERE blog_uid IN " +
            "<foreach item='item' index='index' collection='blogUidList' open='(' separator=',' close=')'> #{item} </foreach> "+
            "GROUP BY blog_uid"+
            "</script>")
    List<BlogCommentRow>  countReplyForBlogList(@Param("blogUidList") List<String> blogUidList);

    /**
     * 获取未满的记录
     */
    @Results(value = {
            @Result(id = true, property = "blogUid", column = "blog_uid"),
            @Result(property = "commentCount", column = "comment_count")
    })
    @Select("SELECT * FROM blog_comment WHERE blog_uid=#{blogUid} AND comment_count<" + COMMENTS_PER_ROW + " ORDER BY dt DESC LIMIT 1")
    BlogCommentRow getFreeRow(String blogUid);

    @Update("DELETE FROM blog_comment WHERE blog_uid=#{blogUid}")
    int deleteByBlogUid(String blogUid);

    @Results(value = {
            @Result(id = true, property = "blogUid", column = "blog_uid"),
            @Result(property = "commentCount", column = "comment_count")
    })
    @Select("SELECT * FROM blog_comment WHERE blog_uid=#{blogUid} AND dt=#{dt}")
    BlogCommentRow getRow(@Param("blogUid")String blogUid,@Param("dt")long dt);
}
