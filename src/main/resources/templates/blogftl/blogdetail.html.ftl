<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
<#include "comm/common.header.ftl">

    <title>${cfg.strBlogDetail} - </title>

</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">

<div class="container">

    <div class="list-group">
        <div class="list-group-item list-group-item-info">
            <div>
                <h1 id="blogTitle"></h1>
                by:&nbsp;<span id="blogAuthor"></span> <br>
                <small><em id="blogDt"></em></small>
            </div>
        </div>
        <div  class="list-group-item">
            <div >
                <a class="btn btn-warning" role="button" id="editBlog" href="#">${cfg.strEdit}</a>
                <a class="btn btn-secondary" role="button" href="#">${cfg.strSetAsHome}</a>
                <a class="btn btn-secondary" role="button" href="#">${cfg.strSetAsAbout}</a>
            </div>
        </div>
        <div  class="list-group-item">
            <div id="blogContent">
            </div>
        </div>

    </div>

</div> <!-- /container -->

<#include "comm/navbar.bottom.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/marked.min.js"></script>
<script src="${cfg.rootUrl}/js/blogdetail.js"></script>
</body>
</html>