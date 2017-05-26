package zb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.blog.service.DbInitService;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


class Hii {
    public String name;
    public  int age;
         public Hii(String name,int age){
            this.name = name;
            this.age = age;
         }
        }

/**
 * Created by zhmt on 2017/5/25.
 */
@RestController()
public class TestRest {

    @Autowired
    private DbInitService dbInitService;

    @RequestMapping("/hi")
    public List<Hii> hi() {
        return Arrays.asList(new Hii("a",1),new Hii("b",2));
    }

    @RequestMapping("/add")
    public String getCourses(String name,String teacher) {
//        Course c = new Course();
//        c.name = name;
//        c.teacher = teacher;
//        courseMapper.insert(c);
        //SET DATABASE DEFAULT TABLE TYPE  CACHED
        //return  courseMapper.getAll();
        //courseMapper.setDefaultTableType();
        dbInitService.init();
        return UUID.randomUUID().toString();
    }
}
