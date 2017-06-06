package zb.blog.security;

import java.lang.annotation.*;

/**
 * Created by zhmt on 2017/6/4.
 */

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired{
}

