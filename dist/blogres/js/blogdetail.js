
function loadBlog() {
    var blogId = $.urlParam('uid');
    if(blogId==null)
        return;

    myget(dataRootUrl+"/blog/meta" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        $("#blogTitle").html(json.title);
        $("#blogAuthor").html(json.author);
        $("#blogDt").html(json.dtStr);
        $("#editBlog").attr("href","postblog.html?uid="+json.uid)
        $("#textBlogId").val(json.uid);
        document.title = document.title +" " + json.title;
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })

    myget(dataRootUrl+"/blog/content" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        var html = json.content;
        //把文件引用换成真实链接
        html = prepareMarkdownText(html);
        html = marked(html);
        $("#blogContent").html(html);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
}

/**
 * 设置home或about页
 * @param url
 */
function setHomeabout(url) {
    var blogUid = $("#textBlogId").val();
    if($.isBlank(blogUid))
        return;
    
    var jqxhr = mypost(dataRootUrl+url , {
        blogUid: blogUid
    }).done(function(data, textStatus, jqXHR) {
        showMsgTip("OK", "OK!!" );
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
}

function initButtonHandlers() {
    $("#buttonSetHome").click(function (e) {
        setHomeabout("/setting/home");
    });
    $("#buttonSetAbout").click(function (e) {
        setHomeabout("/setting/about");
    });
}

setMarked();
loadBlog();
initButtonHandlers();