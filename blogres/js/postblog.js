function postBlog() {
    $("#submit").prop("disabled",true)
    var jqxhr = $.post(dataRootUrl+"/blog" , {
        title:      $("#title").val(),
        author:     $("#author").val(),
        //password:   $("#password").val(),
        content:    $("#content").val()
    }).done(function(data, textStatus, jqXHR) {
        showMsgTip("OK", data );
        //$(location).attr('href','index.html');
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
        $("#submit").prop("disabled",false)
    })
}



$("#navPostBlog").addClass("active");
$("#postBlogForm").submit(function( event ) {
    // Stop form from submitting normally
    event.preventDefault();
    postBlog();
});