package zb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.DatabaseInitor;

import java.sql.SQLSyntaxErrorException;

/**
 * Created by zhmt on 2017/5/26.
 */
@Service
public class DbInitService {
    @Autowired
    private DatabaseInitor databaseInitor;

    public void init() {
        databaseInitor.setDefaultTableType();

        databaseInitor.createTableBlogMeta();
        filterIndexExistException(()->databaseInitor.createBlogMetaIndexUpdatedt());

        databaseInitor.createTableBlogContent();

        databaseInitor.createTableComment();
        filterIndexExistException(()->databaseInitor.createTableCommentIndexUidSeq());
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
