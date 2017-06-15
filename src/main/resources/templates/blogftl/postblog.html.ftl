<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
    <#include "comm/common.header.ftl">

    <title>${cfg.strPostBlog} - ${cfg.blogName}</title>

</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">

<div class="container">
    <h1 class="text-center">${cfg.strPostBlog}</h1>
    <form id="postBlogForm">
        <div class="form-group row">
            <label for="title" class="offset-sm-1 col-sm-1 col-form-label">${cfg.strTitle}</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="title" placeholder="${cfg.strTitleLimit}">
            </div>
        </div>
        <div class="form-group row">
            <label for="author" class="offset-sm-1 col-sm-1 col-form-label">${cfg.strAuthor}</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="author" placeholder="${cfg.strAuthorLimit}">
            </div>
        </div>
        <#--<div class="form-group row">-->
            <#--<label for="password" class="offset-sm-1 col-sm-1 col-form-label">${cfg.strPassword}</label>-->
            <#--<div class="col-sm-10">-->
                <#--<input type="password" class="form-control" id="password" placeholder="${cfg.strPasswordLimit}">-->
            <#--</div>-->
        <#--</div>-->
        <div class="form-group row">
            <label for="content" class="offset-sm-1 col-sm-1 col-form-label">${cfg.strContent}</label>
            <div class="col-sm-10">
                <textarea class="form-control" id="content" rows="20" placeholder="${cfg.strContentLimit}"></textarea>
            </div>
        </div>
        <div class="form-group row">
            <div class="offset-sm-2 col-sm-10">
                <button id="submit" type="submit" class="btn btn-primary">${cfg.strPostBlog}</button>
            </div>
        </div>
    </form>
</div> <!-- /container -->

<#include "comm/msgtip.html.ftl">
<#include "comm/navbar.bottom.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/postblog.js"></script>
</body>
</html>