package zb.blog;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by zhmt on 2017/5/30.
 */
@Component
public class BlogCfg {
    public String blogName  = "ZBLOG";
    public String lang = "cmn"; //en
    public String dataServerIp = "127.0.0.1";
    public int dataServerPort = 8080;
    public String pwd = "admin" ;
    public int blogListPageSize = 2;


    public String strPostBlog = "发表文章";
    public String strTitle = "标题";
    public int maxTitleLen = 128;
    public String strTitleLimit = "标题不能为空,最多%d个汉字或字母";
    public String strAuthor = "作者";
    public int maxAuthorLen = 64;
    public String strAuthorLimit = "作者不能为空,最多%d个汉字或字母";
    public String strPassword = "密码";
    public int maxPasswordLen = 32;
    public String strPasswordPlaceholder = "输入管理密码";
    public String strPasswordLimit = "密码不能为空,最多%d个汉字或字母";
    public String strContent = "正文";
    public int maxContentLen = 10*1024;
    public String strContentPlaceholder = "在此输入正文";
    public String strContentLimit = "正文不能为空,最多%d个字母";

    public String strBlogList = "博客列表";
    public String strNextPage = "下一页";
    public String strLastPage = "上一页";
    public String strTotalPageCount = "总页数";


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

    public String getRootUrl() {
        return String.format("http://%s:%d",dataServerIp,dataServerPort);
    }

    public String getPwd() {
        return pwd;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public int getMaxTitleLen() {
        return maxTitleLen;
    }

    public String getStrTitleLimit() {
        return String.format(strTitleLimit,maxTitleLen);
    }

    public String getStrAuthor() {
        return strAuthor;
    }

    public int getMaxAuthorLen() {
        return maxAuthorLen;
    }

    public String getStrAuthorLimit() {
        return String.format(strAuthorLimit,maxAuthorLen);
    }

    public String getStrPassword() {
        return strPassword;
    }

    public int getMaxPasswordLen() {
        return maxPasswordLen;
    }

    public String getStrPasswordPlaceholder() {
        return strPasswordPlaceholder;
    }

    public String getStrPasswordLimit() {
        return String.format(strPasswordLimit,maxPasswordLen);
    }

    public String getStrContent() {
        return strContent;
    }

    public int getMaxContentLen() {
        return maxContentLen;
    }

    public String getStrContentPlaceholder() {
        return strContentPlaceholder;
    }

    public String getStrContentLimit() {
        return String.format(strContentLimit,maxContentLen);
    }

    public String getStrPostBlog() {
        return strPostBlog;
    }

    public int getBlogListPageSize() {
        return blogListPageSize;
    }

    public String getStrBlogList() {
        return strBlogList;
    }

    public String getStrNextPage() {
        return strNextPage;
    }

    public String getStrLastPage() {
        return strLastPage;
    }

    public String getStrTotalPageCount() {
        return strTotalPageCount;
    }
}
