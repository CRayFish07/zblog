package zb.blog.model;

import java.util.List;

/**
 * Created by zhmt on 2017/6/1.
 */
public class BlogPage {
    public int page;
    public int pageCount;
    public List<List<BlogMeta>> list;
    public int last;
    public int next;

    public int getPage() {
        return page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<List<BlogMeta>> getList() {
        return list;
    }

    public int getLast() {
        return last;
    }

    public int getNext() {
        return next;
    }

}
