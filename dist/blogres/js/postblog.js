function postBlog() {
    $("#submit").prop("disabled",true)
    var jqxhr = mypost(dataRootUrl+"/blog" , {
        title:      $("#title").val(),
        author:     $("#author").val(),
        //password:   $("#password").val(),
        uid:        $("#inputBlogUid").val(),
        content:    $("#content").val()
    }).done(function(data, textStatus, jqXHR) {
        //showMsgTip("OK", data );
        $(location).attr('href','index.html?zblogurl=blogdetail.jsp%3fuid%3d'+jqXHR.responseText);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
        $("#submit").prop("disabled",false)
    })
}

function loadBlog() {
    var blogId = $("#inputBlogUid").val();
    if($.isBlank(blogId))
        return;

    myget(dataRootUrl+"/blog/meta" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        $("#title").val(json.title);
        $("#author").val(json.author);
        //$("#blogDt").html(json.dtStr);
        //$("#editBlog").attr("href","postblog.html?uid="+json.uid)
        //document.title = document.title +" " + json.title;
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })

    myget(dataRootUrl+"/blog/content" , {
        uid:      blogId
    }).done(function(data, textStatus, jqXHR) {
        var json = $.parseJSON(jqXHR.responseText);
        var html = json.content;
        $("#content").val(json.content);
        //html = replaceAll(html,"\n","\n\n");
        //html = marked(html);
        //$("#blogContent").html(html);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
}

$("#navPostBlog").addClass("active");
$("#postBlogForm").submit(function( event ) {
    // Stop form from submitting normally
    event.preventDefault();
    postBlog();
});
loadBlog();