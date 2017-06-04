package zb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.BlogHomeaboutMapper;
import zb.blog.model.BlogHomeabout;

/**
 * Created by zhmt on 2017/6/4.
 */
@Service
public class BlogHomeaboutService {
    private static final String KEY_HOME = "home";
    private static final String KEY_ABOUT = "about";
    @Autowired
    private BlogHomeaboutMapper blogHomeaboutMapper;

    public void setHome(String blogUid) {
        set(KEY_HOME,blogUid);
    }

    public String getHome() {
        return get(KEY_HOME);
    }

    public String getAbout() {
        return get(KEY_ABOUT);
    }

    public void setAbout(String blogUid) {
        set(KEY_ABOUT,blogUid);
    }


    private String get(String homeOrAbout) {
        String ret = blogHomeaboutMapper.get(homeOrAbout);
        return  ret;
    }

    private void set(String homeOrAbout,String blogUid) {
        BlogHomeabout data = new BlogHomeabout();
        data.uid = homeOrAbout;
        data.content = blogUid;
        insertOrUpdate(data);
    }

    private void insertOrUpdate(BlogHomeabout data) {
        if(blogHomeaboutMapper.put(data)<=0) {
            blogHomeaboutMapper.post(data);
        }
    }
}
