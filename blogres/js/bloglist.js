

function bindJumpBlogPage() {
    $("#jumpBlogPageForm").submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        //$("#jumpBlogPageForm").action= "bloglist.html?page="+$("#jumpBlogPageInput").val();
        window.location.href =  "bloglist.html?page="+$("#jumpBlogPageInput").val();
    });
}

function loadBlogListContent() {
    var page = $.urlParam('page');
    if(page==null)
        page = 1;
    else
        page = parseInt(page);
    
    var jqxhr = myget(dataRootUrl+"/blog/listhtml" , {
        page:      page
    }).done(function(data, textStatus, jqXHR) {
        //showMsgTip("OK", data );
        $("#listContent").html(jqXHR.responseText);
        bindJumpBlogPage();
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
}




$("#navBlogList").addClass("active");
loadBlogListContent();


