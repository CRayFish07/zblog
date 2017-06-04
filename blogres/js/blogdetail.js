function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}

function loadBlog() {
    var blogId = $.urlParam('uid');
    if(blogId==null)
        return;

    $.get(dataRootUrl+"/blog/meta" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        $("#blogTitle").html(json.title);
        $("#blogAuthor").html(json.author);
        $("#blogDt").html(json.dtStr);
        $("#editBlog").attr("href","postblog.html?uid="+json.uid)
        document.title = document.title +" " + json.title;
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })

    $.get(dataRootUrl+"/blog/content" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        var html = json.content;
        //html = replaceAll(html,"\n","\n\n");
        html = marked(html);
        $("#blogContent").html(html);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
}

setMarked();
loadBlog();