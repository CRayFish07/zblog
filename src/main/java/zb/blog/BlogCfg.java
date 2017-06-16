package zb.blog;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import zb.blog.service.FileService;
import zb.blog.util.ExceptionUtil;

import java.io.File;
import java.io.IOException;

/**
 * Created by zhmt on 2017/5/30.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class BlogCfg {
    protected static final Logger log = LogManager.getRootLogger();
    private static final File cfgFile = FileService.newFile("blogCfg.txt");

    @Component
    public static class BlogCfgLoader {
        protected static final Logger log = LogManager.getLogger(BlogCfgLoader.class);

        @Bean
        public BlogCfg blogCfg() {
            ObjectMapper m = new ObjectMapper();

            BlogCfg ret = ExceptionUtil.castException(()->{
                String data = FileUtils.readFileToString(cfgFile,"utf-8");
                return m.readValue(data,BlogCfg.class);
            });
            log.info("BLOG CFG LOADED......"+ret.blogName);
            return  ret;
        }
    }



    public String blogName  = "ZBLOG";
    public String lang = "cmn"; //en
    public String dataServerIp = "127.0.0.1";
    public int dataServerPort = 8080;
    public String pwd = "admin" ;
    public int blogListPageSize = 20;

    public String strHome = "主页";
    public String strAbout = "关于";


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
    public String strContentLimit = "正文不能为空,最多%d个字母,支持普通文本、markdown格式";

    public String strBlogList = "文章列表";
    public String strNextPage = "下一页";
    public String strLastPage = "上一页";
    public String strTotalPageCount = "总页数";

    public String strBlogDetail = "文章详情";
    public String strSetAsHome    ="设为主页";
    public String strSetAsAbout     ="设为关于页";
    public String strEdit       ="编辑";

    public String strBlogNotExists = "博文不存在";

    public String strOnlySubdirectoryAllowed = "只能操作子目录";
    public String strMkdir      = "创建目录";
    public String strDelete     = "删除";
    public String strPostFile   = "上传文件";
    public String strInputDirName="输入目录名称";
    public String strDirectoryCantBeBlank = "目录不能为空";
    public String strParentDirectoryCantBeBlank = "父目录不能为空";
    public String strFailedToDeleteFile = "删除文件失败";
    public String strClose = "关闭";
    public String strParentDirectory = "父目录";
    public String strPostFileToDirectory="上传到目录";
    public String strFinishPostFile = "上传完成";
    public String strPleaseDragUploadingFilesHere = "把要上传的文件拖到这里";
    public String strRefUrl ="所选文件引用地址";

    public String strLoginFailed = "FAILED:登录失败!";
    public String strAccessDenied = "DENIED:没有权限,必须登录";
    public String strLogin = "登录";

    public String strComment = "评论";
    public String strAllComments = "所有评论";
    public String strPostComment = "发表评论";
    public int maxCommentLen = 1024;
    public String strCommentLimit = "评论不能为空,最多%d个字母,支持普通文本、markdown格式";
    public String strCommentor = "评论人";
    public String strCommentorLimit = "评论人不能为空,最多%d个汉字或字母";
    public String strCommentDeleted = "该评论已删除";

    public String strRobotCheck = "输入左侧图片中文字";
    public String strChangeRobotCheckImg = "看不清换一张";
    public String strInvalidRobotCheckCode = "验证码错误";

    public String strConfirmDelete  = "确认删除？";
    public String strYes            = "是";
    public String strNo             = "否";


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

    public String getStrHome() {
        return strHome;
    }

    public String getStrAbout() {
        return strAbout;
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

    public String getStrBlogDetail() {
        return strBlogDetail;
    }

    public String getStrSetAsHome() {
        return strSetAsHome;
    }

    public String getStrSetAsAbout() {
        return strSetAsAbout;
    }

    public String getStrEdit() {
        return strEdit;
    }

    public String getStrBlogNotExists() {
        return strBlogNotExists;
    }

    public String getStrOnlySubdirectoryAllowed() {
        return strOnlySubdirectoryAllowed;
    }

    public String getStrMkdir() {
        return strMkdir;
    }

    public String getStrDelete() {
        return strDelete;
    }

    public String getStrPostFile() {
        return strPostFile;
    }

    public String getStrInputDirName() {
        return strInputDirName;
    }

    public String getStrDirectoryCantBeBlank() {
        return strDirectoryCantBeBlank;
    }

    public String getStrParentDirectoryCantBeBlank() {
        return strParentDirectoryCantBeBlank;
    }

    public String getStrFailedToDeleteFile() {
        return strFailedToDeleteFile;
    }

    public String getStrClose() {
        return strClose;
    }

    public String getStrParentDirectory() {
        return strParentDirectory;
    }

    public String getStrPostFileToDirectory() {
        return strPostFileToDirectory;
    }

    public String getStrFinishPostFile() {
        return strFinishPostFile;
    }

    public String getStrPleaseDragUploadingFilesHere() {
        return strPleaseDragUploadingFilesHere;
    }

    public String getStrRefUrl() {
        return strRefUrl;
    }

    public String getStrLoginFailed() {
        return strLoginFailed;
    }

    public String getStrAccessDenied() {
        return strAccessDenied;
    }

    public String getStrLogin() {
        return strLogin;
    }


    public int getMaxCommentLen() {
        return maxCommentLen;
    }

    public String getStrCommentLimit() {
        return String.format(strCommentLimit,maxCommentLen);
    }

    public String getStrCommentor() {
        return strCommentor;
    }

    public String getStrCommentorLimit() {
        return String.format(strCommentorLimit,maxAuthorLen);
    }

    public String getStrComment() {
        return strComment;
    }

    public String getStrPostComment() {
        return strPostComment;
    }

    public String getStrAllComments() {
        return strAllComments;
    }

    public String getStrRobotCheck() {
        return strRobotCheck;
    }

    public String getStrChangeRobotCheckImg() {
        return strChangeRobotCheckImg;
    }

    public String getStrInvalidRobotCheckCode() {
        return strInvalidRobotCheckCode;
    }

    public String getStrCommentDeleted() {
        return strCommentDeleted;
    }

    public String getStrConfirmDelete() {
        return strConfirmDelete;
    }

    public String getStrYes() {
        return strYes;
    }

    public String getStrNo() {
        return strNo;
    }

    public static void main(String[] args) throws IOException {
        BlogCfg cfg = new BlogCfg();
        ObjectMapper m = new ObjectMapper();
        String str = m.writerWithDefaultPrettyPrinter().writeValueAsString(cfg);
        FileUtils.write(cfgFile,str,"utf-8"); ;
        //System.out.println(new BlogCfg().blogCfg().blogName);
    }
}
