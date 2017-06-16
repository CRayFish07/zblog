

function bindJumpBlogPage() {
    $("#jumpBlogPageForm").submit(function( event ) {
        // Stop form from submitting normally
        event.preventDefault();
        url = "index.html?zblogurl=bloglist.jsp%3fpage%3d"+$("#jumpBlogPageInput").val();
        window.location.href =  url;
    });
}

$("#navBlogList").addClass("active");
//loadBlogListContent();
bindJumpBlogPage();

