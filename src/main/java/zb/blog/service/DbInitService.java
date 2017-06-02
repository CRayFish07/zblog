package zb.blog.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.DatabaseInitor;

import javax.annotation.PostConstruct;
import java.sql.SQLSyntaxErrorException;

/**
 * Created by zhmt on 2017/5/26.
 */
@Service
public class DbInitService {
    protected static final Logger log = LogManager.getRootLogger();
    
    @Autowired
    private DatabaseInitor databaseInitor;

    @PostConstruct
    public void init() {
        databaseInitor.setDefaultTableType();

        databaseInitor.createTableBlogMeta();
        filterIndexExistException(()->databaseInitor.createBlogMetaIndexUpdatedt());

        databaseInitor.createTableBlogContent();

        databaseInitor.createTableComment();
        filterIndexExistException(()->databaseInitor.createTableCommentIndexUidSeq());

        log.info("......DATABASE inited......");
    }

     private void filterIndexExistException(Runnable r) {
         try {
             r.run();
         } catch (Exception e) {
             if(!e.getMessage().contains("exist"))
                 throw  e;
         }
     }
}
