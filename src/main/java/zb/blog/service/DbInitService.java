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
        filterException(()->databaseInitor.createBlogMetaIndexUpdatedt(),"BLOG_META_UPDATEDT");

        databaseInitor.createTableBlogContent();

        databaseInitor.createTableComment();
        filterException(()->databaseInitor.renameCommentUidToBlogUid(),"not found");
        databaseInitor.dropTableCommentIndexUidSeq();
        filterException(()->databaseInitor.createTableCommentIndexUidSeq(),"COMMENT_BLOGUID_DT");

        databaseInitor.createTableHomeAndAbout();

        databaseInitor.createTablePvStatLog();
        databaseInitor.createTableArticleStat();

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
}
