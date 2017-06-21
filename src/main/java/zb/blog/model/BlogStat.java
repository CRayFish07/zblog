package zb.blog.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by zhmt on 2017/6/19.
 */
public class BlogStat {
    public String blogUid;
    public String ip;
    public Long justDt; //只有日期，不包括时分秒

    public String getBlogUid() {
        return blogUid;
    }

    public String getIp() {
        return ip;
    }

    public void setJustDt(long justDt) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(justDt);

        Calendar date = Calendar.getInstance();
        date.clear();
        date.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));

        this.justDt = date.getTimeInMillis();
    }

    public Long getJustDt() {
        return justDt;
    }

    public static void main(String[] args) {
        BlogStat stat = new BlogStat();
        stat.setJustDt(System.currentTimeMillis());
    }
}
