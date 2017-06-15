package zb.blog.model;

import java.util.List;

/**
 * Created by zhmt on 2017/6/14.
 */
public class CommentPage {
    public int page;
    public int pageCount;
    public List<BlogComment> list;
    public int last;
    public int next;

    public int getPage() {
        return page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<BlogComment> getList() {
        return list;
    }

    public int getLast() {
        return last;
    }

    public int getNext() {
        return next;
    }
}
