<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
    <meta charset="UTF-8">    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>${cfg.blogName}</title>

    <#include "comm/common.css.ftl">
</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">

<div class="container">

    <!-- Main component for a primary marketing message or call to action -->
    <div class="jumbotron">
        <h1>Navbar example</h1>
        <p>This example is a quick exercise to illustrate how the default, static and fixed to top navbar work. It includes the responsive CSS and HTML, so it also adapts to your viewport and device.</p>
        <p>To see the difference between static and fixed top navbars, just scroll.</p>
        <p>
            <a class="btn btn-lg btn-primary" href="../../components/#navbar" role="button">View navbar docs &raquo;</a>
        </p>
    </div>

</div> <!-- /container -->


<#include "comm/common.js.ftl">
<script src="js/index.js"></script>
</body>
</html>