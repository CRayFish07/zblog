package zb.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zb.blog.cache.StaticResCache;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhmt on 2017/6/6.
 */
@Component
public class WebFilter implements Filter{
    @Autowired
    private StaticResCache staticResCache;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //System.out.println("==>DemoFilter启动");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse rsp = (HttpServletResponse)response;
        //System.out.println(req.getRequestURI());

        if(staticResCache.doFilter(req,rsp)) {
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}