package zb.blog;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import zb.blog.cache.StaticResCache;
import zb.blog.security.ControllerInterceptor;
import zb.blog.security.WebFilter;
import zb.blog.service.FileService;
import zb.blog.util.ExceptionUtil;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhmt on 2017/5/30.
 */
@Configuration
public class SpringMvcCfg extends WebMvcConfigurerAdapter {
    public SpringMvcCfg() {
        FileService.newFile("blogdb").mkdirs();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:./blogres/");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    /**
     * 支持跨域访问,支持跨域带cookie
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    @Autowired
    private ControllerInterceptor controllerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration ir = registry.addInterceptor(controllerInterceptor);
        // 配置拦截的路径
        ir.addPathPatterns("/**");
        // 配置不拦截的路径
//        ir.excludePathPatterns("/**.html");
//        ir.excludePathPatterns("/css/**");
//        ir.excludePathPatterns("/js/**");
//        ir.excludePathPatterns("/upload/**");
    }

    static String uploadTmpDir;
    static {
        uploadTmpDir = ExceptionUtil.castException(()->{return new File("./blogres/tmp").getCanonicalPath();});
        new File(uploadTmpDir).mkdirs();
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadTmpDir);
        factory.setMaxFileSize("1024MB");
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    @Autowired
    private WebFilter webFilter;
    @Bean
    public FilterRegistrationBean getDemoFilter(){
        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(webFilter);
        List<String> urlPatterns=new ArrayList<String>();
        urlPatterns.add("/*");//拦截路径，可以添加多个
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }
    

    
//    @Bean
//    public DataSource dataSource() {
//        DataSource dataSource = new DataSource();
//
//        String fileDir = FileService.newFile("blogdb/blogdb").getPath();
//        dataSource.setUrl("jdbc:hsqldb:file:"+fileDir);
//        dataSource.setUsername("SA");
//        dataSource.setPassword("");
//        dataSource.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
//
//        return dataSource;
//    }
}