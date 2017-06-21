package zb.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.BlogStat;

import java.util.List;

/**
 * Created by zhmt on 2017/6/19.
 */
public interface BlogStatMapper {
    @Update("INSERT INTO blog_stat (blogUid,ip,justDt)VALUES(#{blogUid},#{ip},#{justDt})")
    void post(BlogStat blogStat);

    @Select("SELECT COUNT(*) FROM blog_stat WHERE blogUid=#{blogUid} AND ip=#{ip} AND justDt=#{justDt}")
    int checkExists(BlogStat blogStat);

//    @Select("SELECT * FROM blog_stat WHERE processed=0")
//    List<BlogStat> selectNotProcessed();

//    @Update("UPDATE blog_stat SET processed=1 WHERE blogUid=#{blogUid} AND ip=#{ip} AND justDt=#{justDt}")
//    int setProcessed(BlogStat blogStat);

    @Update("DELETE FROM blog_stat WHERE dt<#{timeNow}")
    int deleteRowsBefore(@Param("timeNow") long timeNow);
}
