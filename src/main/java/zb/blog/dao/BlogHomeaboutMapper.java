package zb.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogHomeabout;

/**
 * Created by zhmt on 2017/6/4.
 */
public interface BlogHomeaboutMapper {
    @Update("INSERT INTO blog_homeabout (uid,content) VALUES(#{uid},#{content})")
    int post(BlogHomeabout row);

    @Update("UPDATE blog_homeabout SET content=#{content} WHERE uid=#{uid}")
    int put(BlogHomeabout row);

    @Select("SELECT content FROM blog_homeabout WHERE uid=#{uid}")
    String get(String uid);
}
