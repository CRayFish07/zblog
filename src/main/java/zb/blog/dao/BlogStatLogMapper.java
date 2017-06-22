package zb.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogStatLog;

import java.util.List;

/**
 * Created by zhmt on 2017/6/19.
 */
public interface BlogStatLogMapper {
    @Update("INSERT INTO blog_statlog (blogUid,ip,justDt)VALUES(#{blogUid},#{ip},#{justDt})")
    void post(BlogStatLog blogStat);

    @Select("SELECT COUNT(*) FROM blog_statlog WHERE blogUid=#{blogUid} AND ip=#{ip} AND justDt=#{justDt}")
    int checkExists(BlogStatLog blogStat);
    
    @Update("DELETE FROM blog_statlog WHERE dt<#{timeNow}")
    int deleteRowsBefore(@Param("timeNow") long timeNow);
}
