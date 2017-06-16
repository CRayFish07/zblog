package zb.blog.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zb.blog.service.BlogService;
import zb.blog.service.FileService;
import zb.blog.util.ExceptionUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by zhmt on 2017/6/6.
 */
@Component
public class StaticResCache {
    public static final File staticRoot = FileService.newFile("./blogres");
    public static final String strStaticRoot = staticRoot.getPath();

    @Autowired
    private BlogService blogService;
    /**
     * @param req
     * @param rsp
     * @return true if the request is filtered.
     */
    public boolean doFilter(HttpServletRequest req, HttpServletResponse rsp) {
//        String etag = req.getHeader("If-None-Match");
//        long timestamp = etag == null ? 0 : Long.parseLong(etag);
        Long updateDt = null;
        long cacheTimeInSeconds = 0;

//        if (isStaticRes(req)) {
//            updateDt = getFileUpdateDt(req.getRequestURI());
//            cacheTimeInSeconds = 300;
//        }else
        if(isBlogRes(req)) {
            updateDt = getBlogResUpdateDt(req);
            cacheTimeInSeconds = 1;
        }


        if(updateDt!=null) {
            if(processEtag(req,rsp,updateDt+"",cacheTimeInSeconds)) {
                return true;
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

    private Long getBlogResUpdateDt(HttpServletRequest request) {
        if(request.getRequestURI().equals("/blog/meta")
                || request.getRequestURI().equals("/blog/content")) {
            String uid = request.getParameter("uid");
            if(StringUtils.isBlank(uid)) {
                return  null;
            }
            return blogService.getMetaUpdatedt(uid);
        }
        return null;
    }

    private static boolean isBlogRes(HttpServletRequest request) {
        if(!"GET".equals(request.getMethod()))
            return false;
        return request.getRequestURI().equals("/blog/meta")
                || request.getRequestURI().equals("/blog/content");
    }

    private Long getFileUpdateDt(String uri) {
        File f = null;
        try {
            f = FileService.newFile(strStaticRoot + uri);
        } catch (Exception e) {
            return  null;
        }
        if(!f.exists()) {
            return null;
        }
        return f.lastModified();
    }

    public static void sendNotModified(HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
    }



    private boolean isStaticRes(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/css/")
                || request.getRequestURI().startsWith("/js/")
                || request.getRequestURI().startsWith("/upload")
                || request.getRequestURI().endsWith(".html");
    }
}
