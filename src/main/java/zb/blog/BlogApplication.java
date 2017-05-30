package zb.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@MapperScan("zb.blog.dao")
@SpringBootApplication
public class BlogApplication extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
}
