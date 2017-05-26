package zb.blog.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by zhmt on 2017/5/25.
 */
public interface CourseMapper {
    @Insert("INSERT INTO course(name,teacher) VALUES(#{name}, #{teacher})")
    void insert(Course user);

    @Select("SELECT * FROM course")
//    @Results({
//            @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
//            @Result(property = "nickName", column = "nick_name")
//    })
    List<Course> getAll();

    @org.apache.ibatis.annotations.Update("SET DATABASE DEFAULT TABLE TYPE  CACHED")
    void setDefaultTableType();
}
