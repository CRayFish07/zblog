package zb.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogMeta;

import java.util.List;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface BlogMetaMapper {
    
    @Update("INSERT INTO blog_meta (uid,dt,updatedt,author,title) VALUES(#{uid},#{dt},#{updatedt},#{author},#{title})")
    public void post(BlogMeta meta);

    @Update("UPDATE blog_meta SET updatedt=#{updatedt},author=#{author},title=#{title} WHERE uid=#{uid}")
    public int put(BlogMeta meta);

    @Update("DELETE FROM blog_meta WHERE uid=#{uid}")
    public int delete(String uid);

    @Select("SELECT * FROM blog_meta WHERE uid=#{uid}")
    public BlogMeta get(String uid);

    @Select("SELECT updatedt FROM blog_meta WHERE uid=#{uid}")
    public Long getUpdatedt(String uid);

    //offset 从 0 开始
    @Select("SELECT * FROM blog_meta ORDER BY updatedt DESC LIMIT #{offset},#{pageSize}")
    public List<BlogMeta> getList(@Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT count(uid) FROM blog_meta")
    public int count();
}
