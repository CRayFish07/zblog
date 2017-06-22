package zb.blog.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.ArticleStatMapper;
import zb.blog.dao.BlogStatLogMapper;
import zb.blog.model.ArticleStat;
import zb.blog.model.BlogStatLog;
import zb.blog.util.ThreadService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhmt on 2017/6/20.
 */
@Service
public class StatService {
    @Autowired
    private BlogStatLogMapper blogStatLogMapper;
    @Autowired
    private ArticleStatMapper articleStatMapper;
    @Autowired
    private ThreadService threadService;

    public void stat(HttpServletRequest req) {
        if (!req.getRequestURI().equals("/blogdetail.jsp")) {
            return;
        }
        String uid = req.getParameter("uid");
        if (StringUtils.isBlank(uid)) {
            return;
        }

        BlogStatLog statLog = new BlogStatLog();
        statLog.ip = req.getRemoteAddr();
        statLog.blogUid = uid;
        statLog.setJustDt(System.currentTimeMillis());

        threadService.exeAndWait(uid, () -> {
            if (blogStatLogMapper.checkExists(statLog) > 0)
                return;
            blogStatLogMapper.post(statLog);
            doIncArticlePv(statLog.blogUid);
        });

    }

    public ArticleStat getArticleStat(String blogUid) {
        return articleStatMapper.get(blogUid);
    }

    public List<ArticleStat> getByIdList(List<String> blogUidList) {
        return articleStatMapper.getByIdList(blogUidList);
    }

    /** 增加文章PV */
    private void doIncArticlePv(String blogUid) {
        ArticleStat stat = articleStatMapper.get(blogUid);
        if(stat==null) {
            stat = new ArticleStat();
            stat.blogUid = blogUid;
            stat.people = 1L;
            articleStatMapper.post(stat);
        } else {
            stat.people++;
            articleStatMapper.put(stat);
        }
    }


}
