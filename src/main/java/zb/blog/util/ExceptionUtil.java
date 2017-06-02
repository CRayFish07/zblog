package zb.blog.util;

/**
 * Created by zhmt on 2017/2/24.
 */
public class ExceptionUtil {
    public interface ExceptionCaster {
        public void run() throws Exception;
    }

    public interface CallableExceptionCaster<R> {
        public R run() throws Exception;
    }

    /**
     * 把异常转成runtime异常
     * @param runable
     */
    public static void castException(ExceptionCaster runable) {
        try {
            runable.run();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static<R> R castException(CallableExceptionCaster<R> runable) {
        try {
            return runable.run();
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
