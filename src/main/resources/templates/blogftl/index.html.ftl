<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
    <#include "comm/common.header.ftl">

    <title>主页 - ${cfg.blogName}</title>

</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1 class="display-3">Jumbotron heading</h1>
        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Sign up today</a></p>
    </div>

</div> <!-- /container -->

<#include "comm/navbar.bottom.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/index.js"></script>
</body>
</html>