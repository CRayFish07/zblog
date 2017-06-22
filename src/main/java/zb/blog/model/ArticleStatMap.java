package zb.blog.model;

import javafx.scene.shape.Arc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhmt on 2017/6/22.
 */
public class ArticleStatMap {
    private Map<String,ArticleStat> map = new HashMap<>();
    public ArticleStatMap(List<ArticleStat> statList) {
        if(statList==null)
            return;
        for(ArticleStat one:statList) {
            map.put(one.blogUid,one);
        }
    }

    public ArticleStat get(String blogUid) {
        return map.get(blogUid);
    }

    public long getPeople(String blogUid) {
        ArticleStat stat =  map.get(blogUid);
        if(stat==null)
            return 0;
        return stat.people==null?0:stat.people;
    }
}
