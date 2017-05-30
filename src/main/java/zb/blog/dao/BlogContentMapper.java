package zb.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogContent;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface BlogContentMapper {
    @Update("INSERT INTO blog_content (uid,updatedt,content) VALUES(#uid,#updatedt,#content)")
    public void post(BlogContent content) ;

    @Update("UPDATE blog_content SET updatedt=#updatedt,content=#content WHERE uid=#uid")
    public void put(BlogContent content) ;

    @Update("DELETE FROM blog_content WHERE uid=#uid")
    public int delete(String uid);

    @Select("SELECT * FROM blog_content WHERE uid=#uid")
    public BlogContent get(String uid);
}
