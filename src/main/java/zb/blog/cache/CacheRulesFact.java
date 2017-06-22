package zb.blog.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import zb.blog.controller.BlogHtmlController;
import zb.blog.service.BlogService;
import zb.blog.util.ExceptionUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhmt on 2017/6/18.
 */

@Component
public class CacheRulesFact {
    private static final int LONG_TERM_CACHE_TIME = 3600*24*30; //in seconds
    
    @Bean
    public List<ClientCacheRule> clientCacheRuleList() {
        List<ClientCacheRule> cacheRules = new LinkedList<>();
        cacheRules.add(new ClientCacheRule("GET","/blog/meta",false,1){
            @Override
            public String getEtag(HttpServletRequest req) {
                return  getBlogResUpdateDt(req);
            }
        });
        cacheRules.add(new ClientCacheRule("GET","/blog/content",false,1){
            @Override
            public String getEtag(HttpServletRequest req) {
                return getBlogResUpdateDt(req);
            }
        });
        cacheRules.add(new ClientCacheRule("GET","/about.jsp",false,LONG_TERM_CACHE_TIME){
            @Override
            public String getEtag(HttpServletRequest req) {
                String ftlName ="blogftl/about.html";
                return Long.toString(getFtlLastModifyTime(ftlName));
            }
        });
        cacheRules.add(new ClientCacheRule("GET","/index1.jsp",false,LONG_TERM_CACHE_TIME){
            @Override
            public String getEtag(HttpServletRequest req) {
                String ftlName ="blogftl/index1.html";
                return Long.toString(getFtlLastModifyTime(ftlName));
            }
        });
        cacheRules.add(new ClientCacheRule("GET","/postfile.jsp",false,LONG_TERM_CACHE_TIME){
            @Override
            public String getEtag(HttpServletRequest req) {
                String ftlName ="blogftl/postfile.html";
                return Long.toString(getFtlLastModifyTime(ftlName));
            }
        });
        cacheRules.add(new ClientCacheRule("GET","/blogdetail.jsp",false,LONG_TERM_CACHE_TIME){
            @Override
            public String getEtag(HttpServletRequest req) {
                String uid = req.getParameter("uid");
                if(uid==null)
                    return null;
                String ftlName = "blogftl/blogdetail.html";
                Long dt = blogService.getMetaUpdatedt(uid);
                Long ftlDt = getFtlLastModifyTime(ftlName);
                String curEtag = (dt+"."+ftlDt);//Integer.toString((dt+""+ftlDt).hashCode());
                return curEtag;
            }
        });
        return cacheRules;
    }


    @Autowired
    private BlogService blogService;

    private String getBlogResUpdateDt(HttpServletRequest request) {
        String uid = request.getParameter("uid");
        if(StringUtils.isBlank(uid)) {
            return  null;
        }
        Long dt = blogService.getMetaUpdatedt(uid);
        if(dt==null)
            return null;
        return dt.toString();
    }

    private static long getFtlLastModifyTime(String ftlName) {
        String name = "/templates/"+ftlName+".ftl";
        URL url = CacheRulesFact.class.getResource(name);
        // FIXED  File f = new File(BlogHtmlController.class.getResource("/templates/"+ftlName+".ftl").getFile());
        //return f.lastModified();
        return ExceptionUtil.castException(()->{return url.openConnection().getLastModified();});
    }

    public static void main(String[] args) {
        System.out.println(getFtlLastModifyTime("blogftl/blogdetail.html"));
    }
}
