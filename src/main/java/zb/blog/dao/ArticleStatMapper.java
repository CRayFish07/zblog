package zb.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import zb.blog.model.ArticleStat;

import java.util.List;

/**
 * Created by zhmt on 2017/6/21.
 */
public interface ArticleStatMapper {
    @Update("INSERT INTO article_stat(blogUid,people) " +
            "VALUES(#{blogUid},#{people})")
    public int post(ArticleStat stat);
    
    @Update("UPDATE article_stat SET people=#{people} " +
            "WHERE blogUid=#{blogUid}")
    public int put(ArticleStat stat);

    @Select("SELECT * FROM article_stat " +
            "WHERE blogUid=#{blogUid}")
    public ArticleStat get(@Param("blogUid")String blogUid);

//    @Select("SELECT * FROM article_stat " +
//            "ORDER BY people LIMIT #{n}")
//    public ArticleStat getTopN(int n);

    @Select("<script> SELECT * FROM article_stat " +
            "WHERE blogUid IN " +
            "<foreach item='item' index='index' collection='blogUidList' open='(' separator=',' close=')'> #{item} </foreach> "+
            "</script>")
    public List<ArticleStat> getByIdList(@Param("blogUidList") List<String> blogUidList);


}
