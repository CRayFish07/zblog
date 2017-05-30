package zb.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogContent;
import zb.blog.model.BlogMeta;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface BlogCommentMapper {
    @Update("INSERT INTO blog_comment (uid,dt,updatedt,comment_count,content) VALUES(#uid,#dt,#updatedt,#commentCount,#content)")
    public void post(BlogContent content) ;

    @Update("UPDATE blog_comment SET updatedt=#updatedt,comment_count=#commentCount,content=#content WHERE uid=#uid and dt=#dt")
    public void put(BlogContent content) ;

//    @Update("DELETE FROM blog_comment WHERE uid=#uid")
//    public int delete(String uid);

    //page 从0开始
    @Select("SELECT * FROM blog_comment WHERE uid=#uid ORDER BY dt DESC LIMIT #page,1 ")
    public BlogContent get(String uid,int page);

    //统计一篇博客有几页评论
    @Select("SELECT COUNT(uid,dt) FROM blog_comment ")
    public int countForOneBlog(String uid) ;
}
