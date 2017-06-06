function postBlog() {
    $("#submit").prop("disabled",true)
    var jqxhr = $.post(dataRootUrl+"/blog" , {
        title:      $("#title").val(),
        author:     $("#author").val(),
        //password:   $("#password").val(),
        uid:        $.urlParam("uid"),
        content:    $("#content").val()
    }).done(function(data, textStatus, jqXHR) {
        //showMsgTip("OK", data );
        $(location).attr('href','blogdetail.html?uid='+jqXHR.responseText);
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
        $("#submit").prop("disabled",false)
    })
}

function loadBlog() {
    var blogId = $.urlParam('uid');
    if(blogId==null)
        return;

    $.get(dataRootUrl+"/blog/meta" , {
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

    $.get(dataRootUrl+"/blog/content" , {
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