package zb.blog.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhmt on 2017/6/22.
 */
public class BlogCommentRowMap {
    private Map<String,BlogCommentRow> map = new HashMap<>();
    public BlogCommentRowMap(List<BlogCommentRow> statList) {
        if(statList==null)
            return;
        for(BlogCommentRow one:statList) {
            map.put(one.blogUid,one);
        }
    }

    public long getCommentCount(String blogUid) {
        BlogCommentRow stat =  map.get(blogUid);
        if(stat==null)
            return 0;
        return stat.commentCount==null?0:stat.commentCount;
    }
}
