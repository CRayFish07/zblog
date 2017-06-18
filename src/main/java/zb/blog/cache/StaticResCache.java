package zb.blog.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zb.blog.controller.BlogHtmlController;
import zb.blog.service.BlogService;
import zb.blog.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhmt on 2017/6/6.
 */
@Component
public class StaticResCache {
    public static final File staticRoot = FileService.newFile("./blogres");
    public static final String strStaticRoot = staticRoot.getPath();

    @Autowired
    private List<ClientCacheRule> clientCacheRuleList = new LinkedList<>();

    /**
     * @param req
     * @param rsp
     * @return true if the request is filtered.
     */
    public boolean doFilter(HttpServletRequest req, HttpServletResponse rsp) {
        for(ClientCacheRule rule: clientCacheRuleList) {
            if(rule.match(req)) {
                 if(processEtag(req,rsp,rule.getEtag(req),rule.cacheTimeInSeconds)) {
                     return true;
                 }
            }
        }
        return false;
    }

    /**
     * 能否直接返回
     * @param req
     * @param rsp
     * @param curEtag
     * @param cacheTimeInSeconds
     * @return
     */
    public static boolean processEtag(HttpServletRequest req,HttpServletResponse rsp,String curEtag,long cacheTimeInSeconds) {
        String etag = req.getHeader("If-None-Match");

        rsp.addHeader("Cache-Control", "private, max-age=" + cacheTimeInSeconds);
        if(etag!=null && etag.equals(curEtag)) {
            sendNotModified(rsp);
            return true;
        } else {
            rsp.addHeader("ETag", "" + curEtag);
        }

        return false;
    }



    public static void sendNotModified(HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
    }
}
