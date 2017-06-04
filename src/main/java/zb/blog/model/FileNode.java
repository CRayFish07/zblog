package zb.blog.model;

/**
 * Created by zhmt on 2017/6/3.
 */
public class FileNode {
    public String id;
    public String text;
    public boolean children;
    public String icon ;

    public FileNode(String id, String text, boolean children,String icon) {
        this.id = id;
        this.text = text;
        this.children = children;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isChildren() {
        return children;
    }

    public String getIcon() {
        return icon;
    }
}
