package zb.blog.util;

import io.github.lukehutch.fastclasspathscanner.FastClasspathScanner;
import io.github.lukehutch.fastclasspathscanner.matchprocessor.ClassMatchProcessor;
import zb.blog.controller.BlogController;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by zhmt on 2017/6/4.
 */
public class ReflectionUtil {
    public static Set<Class<?>> reflectPackage(String packageName) {
        final Set<Class<?>> classes = new HashSet<>();
        new FastClasspathScanner(packageName)
                .matchAllClasses(new ClassMatchProcessor() {
                    @Override
                    public void processMatch(Class<?> klass) {
                        classes.add(klass);
                    }
                }).scan();
        return classes;
    }

    public static List<Class<?>> getClassHasAnnotation(String packageName,Class<? extends Annotation> annotation) {
        Set<Class<?>> set = ReflectionUtil.reflectPackage(BlogController.class.getPackage().getName());
        List<Class<?>> ret = new ArrayList<>();
        for(Class<?> cls : set) {
            if((cls.isAnnotationPresent(annotation) )) {
                ret.add(cls);
            }
        }
        return ret;
    }
    
    public static List<Method> getMethodHasAnnotation(Class<?> cls,Class<? extends Annotation> annotation) {
        List<Method> ret = new ArrayList<>();
        for(Method m : cls.getDeclaredMethods()) {
            if((m.isAnnotationPresent(annotation)))
                ret.add(m);
        }
        return ret;
    }
}
