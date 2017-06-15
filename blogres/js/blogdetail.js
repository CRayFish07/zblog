var curCommentData = null;

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
        loadComments(1);
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

/**
 * 发表评论
 */
function postComment() {
    var blogUid = $("#textBlogId").val();
    if($.isBlank(blogUid))
        return;
    var url = "/blog/comment";
    mypost(dataRootUrl+url , {
        blogUid:    blogUid,
        commentor:  $("#inputCommentor").val(),
        comment:    $("#textareaComment").val(),
        robotCheckCode:$("#inputRobotCheckCode").val()
    }).done(function(data, textStatus, jqXHR) {
        loadComments(1);
        $("#textareaComment").val("")
        showMsgTip("OK", "OK!!" );
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    }).always(function () {
        changeRobotCheckImg();
        $("#inputRobotCheckCode").val("");
    })

}

/**
 * 初始化button点击处理函数
 */
function initButtonHandlers() {
    $("#buttonSetHome").click(function (e) {
        setHomeabout("/setting/home");
    });
    $("#buttonSetAbout").click(function (e) {
        setHomeabout("/setting/about");
    });
    $("#formPostComment").submit( function (e) {
        event.preventDefault();
        postComment();
    });
}



function loadComments(pageNum) {
    var blogUid = $("#textBlogId").val();
    if($.isBlank(blogUid))
       return;
    
    var sample = $("#divCommentSample");
    var spanDt = $("#divCommentSample > div.list-group-item.list-group-item-success > div > div > span:nth-child(1)");
    var spanCommentor = $("#divCommentSample > div.list-group-item.list-group-item-success > div > div > span:nth-child(3)");
    var divComment = $("#divCommentSample > div:nth-child(2) > div > div");
    var listGroupComment = $("#listGroupComment");

    var url = "/blog/comment";
    myget(dataRootUrl+url , {
        blogUid:    blogUid,
        page:       pageNum
    }).done(function(data, textStatus, jqXHR) {
        var rsp = jqXHR.responseText;
        if(!$.isBlank(rsp)) {
            listGroupComment.html("");
            var json = $.parseJSON(jqXHR.responseText);
            curCommentData = json;
            $("#inputJumpCommentPage").val(json.page);
            $("#spanPageCount").text(json.pageCount);
            for(var i=0;i<json.list.length; i++) {
                spanDt.html(json.list[i].dtStr);
                spanCommentor.html(json.list[i].author);
                var html = prepareMarkdownText(json.list[i].comment);
                html = marked(html);
                divComment.html(html);
                listGroupComment.append(sample.html());
            }
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
}

function loadCommentLastPage() {
    loadComments(curCommentData.last);
}

function loadCommentNextPage() {
    loadComments(curCommentData.next);
}

function loadCommentJumpPage() {
    loadComments( $("#inputJumpCommentPage").val());
}

function changeRobotCheckImg() {
    $("#imgRobotCheckImg").prop("src", dataRootUrl+"/user/robotcheck/img") ;
}

setMarked();
loadBlog();
initButtonHandlers();