package zb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zb.blog.dao.DatabaseInitor;

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
    }
}
