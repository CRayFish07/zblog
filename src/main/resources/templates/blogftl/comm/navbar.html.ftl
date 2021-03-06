<!-- Fixed navbar -->

<nav class="navbar navbar-toggleable-sm navbar-inverse fixed-top bg-inverse">
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <a class="navbar-brand" href="index.html">${cfg.blogName}</a>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav mr-auto">
            <li id="navHome" class="nav-item">
                <a class="nav-link" href="index.html">${cfg.strHome} <span class="sr-only">(current)</span></a>
            </li>
            <li id="navBlogList" class="nav-item">
                <a class="nav-link" href="index.html?zblogurl=bloglist.jsp">${cfg.strBlogList} </a>
            </li>
            <li id="navAbout" class="nav-item">
                <a class="nav-link" href="index.html?zblogurl=about.jsp">${cfg.strAbout}</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li id="navLogin" class="nav-item" >
                <a class="nav-link" href="javascript:showLogin()">${cfg.strLogin}</a>
            </li>
            <li id="navPostBlog" class="nav-item" >
                <a class="nav-link" href="index.html?zblogurl=postblog.jsp">${cfg.strPostBlog}</a>
            </li>
            <li id="navPostFile" class="nav-item" >
                <a class="nav-link" target="_blank" href="index.html?zblogurl=postfile.jsp">${cfg.strPostFile}</a>
            </li>
        </ul>
    </div>
</nav>

<#include "login.html.ftl">