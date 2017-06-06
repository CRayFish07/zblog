package zb.blog.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import zb.blog.BlogCfg;
import zb.blog.controller.BlogController;
import zb.blog.util.ReflectionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhmt on 2017/6/4.
 */

@Component
public class ControllerInterceptor extends HandlerInterceptorAdapter {
    protected static final Logger log = LoggerFactory.getLogger(ControllerInterceptor.class);

    @Autowired
    private BlogCfg blogCfg;

    public  ControllerInterceptor() {
        List<Class<?>> classList = ReflectionUtil.getClassHasAnnotation(BlogController.class.getPackage().getName(),RestController.class);
        classList.addAll(ReflectionUtil.getClassHasAnnotation(BlogController.class.getPackage().getName(),Controller.class));

        List<Method> methodList = new ArrayList<>();
        for(Class<?> cls : classList) {
            methodList.addAll(ReflectionUtil.getMethodHasAnnotation(cls, LoginRequired .class));
        }

        for (Method m:methodList) {
            Condition c = parseCondition(m);
            if(c!=null)
                conditions.add(c);
        }
    }

    private Condition  parseCondition(Method m) {
          String method = null;
          String uri = null;

          if(m.isAnnotationPresent(PostMapping.class))  {
              PostMapping annotation = m.getAnnotation(PostMapping.class);
              uri = annotation.value()[0];
              method = "POST";
          }else if(m.isAnnotationPresent(PutMapping.class))  {
              PutMapping annotation = m.getAnnotation(PutMapping.class);
              uri = annotation.value()[0];
              method = "PUT";
          } else if(m.isAnnotationPresent(GetMapping.class)) {
              GetMapping annotation = m.getAnnotation(GetMapping.class);
              uri = annotation.value()[0];
              method = "GET";
          }  else {
              return null;
          }

          return new Condition(method,uri);
    }

    static class Condition {
        private String method;
        private String uri;

        public Condition(String method, String uri) {
            this.method = method;
            this.uri = uri;
        }

        public boolean match(HttpServletRequest request) {
            return request.getMethod().equals(method) && request.getRequestURI().equals(uri);
        }

        @Override
        public String toString() {
            return "Condition{" +
                    "method='" + method + '\'' +
                    ", uri='" + uri + '\'' +
                    '}';
        }
    }

    private static List<Condition> conditions = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        for(Condition cond:conditions) {
            if(cond.match(request)) {
                //System.out.println("match \t " + request.getRequestURI());
                if(request.getSession().getAttribute("login")==null) {
                    throw new RuntimeException(blogCfg.strAccessDenied);
                }
            }
        }
        return true;
    }
}
