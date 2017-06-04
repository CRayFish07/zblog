<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
    <#include "comm/common.header.ftl">

    <title>${cfg.strHome} - ${cfg.blogName}</title>

</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="list-group">
        <div class="list-group-item list-group-item-info">
            <div>
                <h1 id="blogTitle"></h1>
                by:&nbsp;<span id="blogAuthor"></span> <br>
                <small><em id="blogDt"></em></small>
            </div>
        </div>
        <div  class="list-group-item">
            <div id="blogContent">
            </div>
        </div>
    </div>

</div> <!-- /container -->

<#include "comm/navbar.bottom.html.ftl">
<#include "comm/msgtip.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/marked.min.js"></script>
<script src="${cfg.rootUrl}/js/index.js"></script>
</body>
</html>