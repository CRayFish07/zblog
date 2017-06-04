<!-- Fixed navbar -->

<nav class="navbar navbar-toggleable-md navbar-inverse fixed-top bg-inverse">
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
                <a class="nav-link" href="bloglist.html">${cfg.strBlogList} </a>
            </li>
            <li id="navAbout" class="nav-item">
                <a class="nav-link" href="about.html">${cfg.strAbout}</a>
            </li>
        </ul>
        <ul class="navbar-nav">
            <li id="navPostBlog" class="nav-item" >
                <a class="nav-link" href="postblog.html">${cfg.strPostBlog}</a>
            </li>
            <li id="navPostFile" class="nav-item" >
                <a class="nav-link" target="_blank" href="postfile.html">${cfg.strPostFile}</a>
            </li>
        </ul>
    </div>
</nav>