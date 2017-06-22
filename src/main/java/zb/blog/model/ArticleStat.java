package zb.blog.model;

import org.msgpack.MessagePack;
import zb.blog.util.ExceptionUtil;

/**
 * 数据库记录
 * Created by zhmt on 2017/6/21.
 */
public class ArticleStat {
    public String blogUid;
    //private byte[] stat;
    public Long people = 0L;

    public String getBlogUid() {
        return blogUid;
    }

    public Long getPeople() {
        return people;
    }

    //    public ArticleStatInfo getStatObj() {
//        MessagePack pack = new MessagePack();
//        ArticleStatInfo ret = ExceptionUtil.castException(()->{
//            return pack.read(stat,new ArticleStatInfo()) ;
//        });
//        return ret;
//    }
//
//    public void setStatObj(ArticleStatInfo info) {
//        MessagePack pack = new MessagePack();
//        stat = ExceptionUtil.castException(()->{
//            return pack.write(info);
//        });
//    }

}
