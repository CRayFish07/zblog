package zb.blog;

/**
 * Created by zhmt on 2017/5/30.
 */
public class BlogCfg {
    public String blogName  = "ZBLOG";
    public String lang = "cmn"; //en
    public String dataServerIp = "127.0.0.1";
    public int dataServerPort = 8080;
    public String pwd = "admin" ;


    public String getBlogName() {
        return blogName;
    }

    public String getLang() {
        return lang;
    }

    public String getDataServerIp() {
        return dataServerIp;
    }

    public int getDataServerPort() {
        return dataServerPort;
    }

    public String getPwd() {
        return pwd;
    }
}
