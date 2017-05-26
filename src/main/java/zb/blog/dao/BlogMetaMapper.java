package zb.blog.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogMeta;

/**
 * Created by zhmt on 2017/5/26.
 */
public interface BlogMetaMapper {
    @Update("INSERT INTO blog_meta (uid,dt,updatedt,author,title) VALUES(#uid,#dt,#updatedt,#author,#title)")
    public void post(BlogMeta meta);

    @Update("UPDATE blog_meta SET dt=#dt,updatedt=#updatedt,author=#author,title=#title WHERE uid=#uid")
    public int put(BlogMeta meta);

    @Select("SELECT * FROM blog_meta WHERE uid=#uid")
    public void get(String uid);

    @Select("SELECT * FROM blog_meta ORDER BY updatedt DESC LIMIT #offset,#pageSize")
    public void get(int offset,int pageSize);
}
