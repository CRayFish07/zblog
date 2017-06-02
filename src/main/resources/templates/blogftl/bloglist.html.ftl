<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
    <#include "comm/common.header.ftl">

    <title>${cfg.strBlogList} - ${cfg.blogName}</title>

</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">

<div id="listContent" class="container">

    
  
</div> <!-- /container -->

<#include "comm/msgtip.html.ftl">
<#include "comm/navbar.bottom.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/bloglist.js"></script>
</body>
</html>