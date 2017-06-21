package zb.blog.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.DatabaseInitor;

import javax.annotation.PostConstruct;

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
        filterExistsException(()->databaseInitor.addColToTableBlogMeta());
        filterExistsException(()->databaseInitor.createBlogMetaIndexUpdatedt());

        databaseInitor.createTableBlogContent();

        databaseInitor.createTableComment();
        filterException(()->databaseInitor.renameCommentUidToBlogUid(),"not found");
        databaseInitor.dropTableCommentIndexUidSeq();
        filterExistsException(()->databaseInitor.createTableCommentIndexUidSeq());

        databaseInitor.createTableHomeAndAbout();

        databaseInitor.createTablePvStatLog();

        log.info("......DATABASE inited......");
    }

    private void filterException(Runnable r, String str) {
        try {
            r.run();
        } catch (Exception e) {
            if(!e.getMessage().contains(str))
                throw  e;
        }
    }

     private void filterExistsException(Runnable r) {
         filterException(r,"exist");
     }
}
