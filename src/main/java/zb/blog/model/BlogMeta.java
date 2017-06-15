package zb.blog.model;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by zhmt on 2017/5/26.
 */
public class BlogMeta {
    public String uid ;  //blogid
    public Long dt ;
    public Long updatedt;
    public String author;
    public String title;

    public String getUid() {
        return uid;
    }

    public Long getDt() {
        return dt;
    }

    public Long getUpdatedt() {
        return updatedt;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDtStr() {
        return formatDate(dt);
    }

    public static String formatDate(long time) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(new Date(time));
    }
}
