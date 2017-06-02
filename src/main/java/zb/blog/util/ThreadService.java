package zb.blog.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使对同一个博文的操作串行化
 * Created by zhmt on 2017/5/31.
 */
@Component
public class ThreadService {
    private static final ExecutorService[] exeArr;
    static {
        exeArr = new ExecutorService[Runtime.getRuntime().availableProcessors() * 2];
        for (int i = 0; i < exeArr.length; i++) {
            exeArr[i] = Executors.newSingleThreadExecutor();
        }
    }

    public void exeAndWait(String postUid,Runnable r) {
        ExecutorService es = getExecutor(postUid);
        ExceptionUtil.castException(()->es.submit(r).get());
    }

    public <T> T exeAndWait(String postUid,Callable<T> r) {
        ExecutorService es = getExecutor(postUid);
        T ret = ExceptionUtil.castException(()-> {return es.submit(r).get();});
        return ret;
    }

    private ExecutorService getExecutor(String postUid) {
         int index = Math.abs(postUid.hashCode());
         index %= exeArr.length;
         return  exeArr[index];
    }
}
