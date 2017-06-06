package zb.blog;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import zb.blog.security.ControllerInterceptor;
import zb.blog.service.FileService;
import zb.blog.util.ExceptionUtil;

import javax.servlet.MultipartConfigElement;
import java.io.File;

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

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
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