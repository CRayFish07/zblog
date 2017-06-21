package zb.blog.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.BlogStatMapper;
import zb.blog.model.BlogStat;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhmt on 2017/6/20.
 */
@Service
public class StatService {
    @Autowired
    private BlogStatMapper blogStatMapper;

    public void stat(HttpServletRequest req) {
        if(!req.getRequestURI().equals("/blogdetail.jsp")) {
            return;
        }
        String uid = req.getParameter("uid");
        if(StringUtils.isBlank(uid)) {
            return;
        }
        
        BlogStat stat = new BlogStat();
        stat.ip = req.getRemoteAddr();
        stat.blogUid = uid;
        stat.setJustDt(System.currentTimeMillis());


    }
}
