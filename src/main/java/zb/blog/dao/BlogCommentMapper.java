package zb.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogCommentRow;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface BlogCommentMapper {
    @Update("INSERT INTO blog_comment (uid,dt,comment_count,content) VALUES(#{uid},#{dt},#{commentCount},#{content})")
    public void post(BlogCommentRow content) ;

    @Update("UPDATE blog_comment SET comment_count=#{commentCount},content=#{content} WHERE uid=#{uid} and dt=#{dt}")
    public void put(BlogCommentRow content) ;

//    @Update("DELETE FROM blog_comment WHERE uid=#{uid}")
//    public int delete(String uid);

    //page 从0开始
    @Select("SELECT * FROM blog_comment WHERE uid=#{uid} ORDER BY dt DESC LIMIT #{page},1 ")
    public BlogCommentRow get(String uid, int page);

    //统计一篇博客有几页评论
    @Select("SELECT COUNT(uid,dt) FROM blog_comment ")
    public int countForOneBlog(String uid) ;
}
