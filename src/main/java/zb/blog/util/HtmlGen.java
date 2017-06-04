package zb.blog.util;

import freemarker.template.Template;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.BlogCfg;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by zhmt on 2017/5/30.
 */

class Ftl {
    String fileName;
    boolean deleted;

    public Ftl(String fileName, boolean deleted) {
        this.fileName = fileName;
        this.deleted = deleted;
    }
}

@Service
public class HtmlGen {
    protected static final Logger log = LogManager.getRootLogger();

    @Autowired
    private BlogCfg blogCfg;
    @Autowired
    private freemarker.template.Configuration freeMarkerConfiguration;

    private static Ftl[] allFiles = new Ftl[]{
            new Ftl("index.html.ftl",false),
            new Ftl("var.js.ftl",false),
            new Ftl("bloglist.html.ftl",false),
            new Ftl("about.html.ftl",false),
            new Ftl("postblog.html.ftl",false) ,
            new Ftl("postfile.html.ftl",false) ,
            //new Ftl("style.min.css.ftl",false) ,
            new Ftl("blogdetail.html.ftl",false)
    };

    public HtmlGen() {

    }

    @PostConstruct
    public  void  init() {
        //System.out.println("=====================================-"+freeMarkerConfiguration);
        webFileGen();
    }

    public void webFileGen() {
        Arrays.stream(allFiles).forEach((Ftl ftl)->{
            if(ftl.deleted) {
               new File(getDestFileName(ftl)).delete();
               return;
            }
            if(ftl.fileName.equals("style.min.css.ftl"))
                ExceptionUtil.castException(()-> processJsTreeCss(ftl));
            else
                ExceptionUtil.castException(()->genOne(ftl));
        });
    }

    private void genOne(Ftl ftl) throws Exception{
        Template template = freeMarkerConfiguration.getTemplate("blogftl/"+ftl.fileName);
        HashMap<String,Object> model = new HashMap<>();
        model.put("cfg",new BlogCfg());

        String fName = getDestFileName(ftl);
        log.info("Creating static file : \t"+fName);
        OutputStreamWriter w = new OutputStreamWriter(new FileOutputStream(fName));//
        template.process(model,w);
        w.flush();
        w.close();
    }

    private void processJsTreeCss(Ftl ftl)  throws Exception {
//        Template template = freeMarkerConfiguration.getTemplate("blogftl/"+ftl.fileName);
//        String fName = getDestFileName(ftl);
//        log.info("Creating static file : \t"+fName);
//        String source = template.toString();
//        source.replaceAll("32px.png",blogCfg.getRootUrl()+"/css/32px.png") ;
//        source.replaceAll("throbber.gif",blogCfg.getRootUrl()+"/css/throbber.gif") ;
//        source.replaceAll("40px.png",blogCfg.getRootUrl()+"/40px.png") ;
    }

    private String getDestFileName(Ftl ftl) {
         String ret = FilenameUtils.getBaseName(ftl.fileName);
         if(ret.endsWith(".js")) {
             ret =  "blogres/js/"+ret;
         } else if(ret.endsWith(".css")){
             ret =  "blogres/css/"+ret;
         }else {
             ret = "blogres/"+ret;
         }
         return  ret;
    }

    public static void main(String[] args) {
         System.out.println( UUID.randomUUID().toString().replaceAll("-",""));
    }
}
