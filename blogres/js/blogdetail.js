var curCommentData = null;

function loadBlog() {
    var blogId = $("#textBlogId").val();
    if($.isBlank(blogId))
        return;

    myget(dataRootUrl+"/blog/meta" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        $("#blogTitle").html(json.title);
        $("#blogAuthor").html(json.author);
        $("#blogDt").html(json.dtStr);
        $("#editBlog").attr("href","index.html?zblogurl=postblog.jsp%3fuid%3d"+json.uid)
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

function deleteBlog() {
    showConfirmDeleteWnd(function () {
        var blogUid = $("#textBlogId").val();
        if($.isBlank(blogUid))
            return;

        var url = "/blog/delete";
        mypost(dataRootUrl+url , {
            uid:    blogUid
        }).done(function(data, textStatus, jqXHR) {
            location.reload();
        }).fail(function(jqXHR, textStatus, errorThrown) {
            var json = $.parseJSON(jqXHR.responseText);
            showMsgTip( "ERR: " + textStatus, json.message );
        })
    });
}

/**
 * 初始化button点击处理函数
 */
function initButtonHandlers() {
    $("#buttonDeleteBlog").click(function () {
        deleteBlog();
    })
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
    var spanDt = $("#divCommentSample > div.list-group-item.list-group-item-success > div > div:nth-child(1) > span:nth-child(1)");
    var spanCommentor = $("#divCommentSample > div.list-group-item.list-group-item-success > div > div:nth-child(1) > span:nth-child(3)");
    var divComment = $("#divCommentSample > div:nth-child(2) > div > div");
    var listGroupComment = $("#listGroupComment");

    var inputHidden = $("#divCommentSample > div.list-group-item.list-group-item-success > div > div:nth-child(2) > textarea");

    var url = "/blog/comment";
    myget(dataRootUrl+url , {
        blogUid:    blogUid,
        page:       pageNum
    }).done(function(data, textStatus, jqXHR) {
        var rsp = jqXHR.responseText;
        if(!$.isBlank(rsp)) {
            listGroupComment.html("");
            var json = JSON.parse(jqXHR.responseText);
            curCommentData = json;
            $("#inputJumpCommentPage").val(json.page);
            $("#spanPageCount").text(json.pageCount);
            for(var i=0;i<json.list.length; i++) {
                spanDt.text(json.list[i].dtStr);
                spanCommentor.text(json.list[i].author);
                inputHidden.val(JSON.stringify(json.list[i]));
                var html = prepareMarkdownText(json.list[i].comment);
                html = marked(html);
                divComment.html(html);
                var firstChild = sample.children().first();
                listGroupComment.append(firstChild.clone());
                listGroupComment.append(firstChild.next().clone());
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

function deleteComment(btn) {
    showConfirmDeleteWnd(function () {
        var commentData = JSON.parse($(btn).next().val());
        var postData = {
            blogUid:commentData.blogUid,
            rowDt:commentData.rowDt,
            author:commentData.author,
            dt : commentData.dt
        };
        var url = "/blog/comment/delete";
        mypost(dataRootUrl+url , postData
        ).done(function(data, textStatus, jqXHR) {
            loadComments(curCommentData.page);
        }).fail(function(jqXHR, textStatus, errorThrown) {
            var json = $.parseJSON(jqXHR.responseText);
            showMsgTip( "ERR: " + textStatus, json.message );
        })
    });
}

function changeRobotCheckImg() {
    $("#imgRobotCheckImg").prop("src", dataRootUrl+"/user/robotcheck/img") ;
}

setMarked();
loadBlog();
initButtonHandlers();