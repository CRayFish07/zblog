package zb.blog.cache;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhmt on 2017/6/18.
 */
public class ClientCacheRule {
    public String httpMethod = "GET";
    public String url;
    public boolean isRegex = false;
    public long cacheTimeInSeconds = 1;

    ClientCacheRule(String httpMethod,String url,boolean isRegex,long cacheTimeInSeconds) {
        this.httpMethod = httpMethod;
        this.url = url;
        this.isRegex = isRegex;
        this.cacheTimeInSeconds = cacheTimeInSeconds;
    }

    public String getEtag(HttpServletRequest req){ return  null; };

    /**
     * 方法和url是否匹配
     * @param req
     * @return
     */
    public boolean match(HttpServletRequest req) {
        if(!req.getMethod().equalsIgnoreCase(httpMethod)) {
            return false;
        }
        if(isRegex) {
            return req.getRequestURI().matches(url);
        }
        return req.getRequestURI().equals(url);
    }
}
