package zb.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogCommentRow;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface BlogCommentMapper {
    /** 每条数据库记录，存储10条评论，博客部署之后，不要修改这个数字 */
    public static  final int COMMENTS_PER_ROW = 10;
    
    @Update("INSERT INTO blog_comment (blog_uid,dt,comment_count,content) VALUES(#{blogUid},#{dt},#{commentCount},#{content})")
    public void post(BlogCommentRow content) ;

    @Update("UPDATE blog_comment SET comment_count=#{commentCount},content=#{content} WHERE blog_uid=#{blogUid} AND dt=#{dt}")
    public void put(BlogCommentRow content) ;

    //page 从0开始
    @Select("SELECT * FROM blog_comment WHERE blog_uid=#{blogUid} ORDER BY dt DESC LIMIT #{page},1 ")
    public BlogCommentRow get(String blogUid, int page);


    @Select("SELECT dt,comment_count FROM blog_comment WHERE blog_uid=#{blogUid} ORDER BY dt DESC LIMIT #{page},1 ")
    public BlogCommentRow getPageEtag(String blogUid, int page);

    //统计一篇博客有几页评论
    @Select("SELECT COUNT(uid,dt) FROM blog_comment WHERE blog_uid=#{blogUid}")
    public int countForOneBlog(String blogUid) ;

    /** 获取未满的记录 */
    @Select("SELECT * FROM blog_comment WHERE blog_uid=#{blogUid} AND comment_count<"+COMMENTS_PER_ROW+" ORDER BY dt DESC LIMIT 1")
    public BlogCommentRow getFreeRow(String blogUid);


}
