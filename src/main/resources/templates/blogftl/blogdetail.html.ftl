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
    <!--文章详情-->
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
                <input id="buttonDeleteBlog" class="btn btn-danger" type="button" value="${cfg.strDelete}" >
                <input id="buttonSetHome" class="btn btn-secondary" type="button" value="${cfg.strSetAsHome}">
                <input id="buttonSetAbout" class="btn btn-secondary" type="button" value="${cfg.strSetAsAbout}">
                <input id="textBlogId" type="text" value="${blogUid}" hidden>
            </div>
        </div>
        <div class="list-group-item">
            <div id="blogContent">
            </div>
        </div>
    </div>  <!--文章详情-->


    <!--发表评论-->
    <div class="list-group offset-1 col-10" style="margin-top: 2rem">
        <div  class="list-group-item list-group-item-success">
            <form id="formPostComment" class="col-12" >
                <div class="form-group ">
                    <label for="inputCommentor" >${cfg.strCommentor}</label>
                    <input type="text" class="form-control" id="inputCommentor" placeholder="${cfg.strCommentorLimit}">
                </div>
                <div class="form-group">
                    <label for="textareaComment" >${cfg.strComment}</label>
                    <textarea class="form-control" id="textareaComment" rows="6" placeholder="${cfg.strCommentLimit}"></textarea>
                </div>
                <div class="form-inline">
                    <img id="imgRobotCheckImg" class="rounded" width="150" height="45" src="${cfg.rootUrl}/user/robotcheck/img"> &nbsp;
                    <input type="text" class="form-control form-control-sm" id="inputRobotCheckCode" placeholder="${cfg.strRobotCheck}"> &nbsp;
                    <button type="button" class="btn btn-success btn-sm" onclick="changeRobotCheckImg()">${cfg.strChangeRobotCheckImg}</button> &nbsp;
                </div>
                <div class="form-group"></div>
                <div class="form-group ">
                    <button type="submit" class="btn btn-primary">${cfg.strPostComment}</button>
                </div>
            </form>
        </div>
    </div> <!--发表评论-->

    <!--查看评论-->
    <div hidden id="divCommentSample" hidden>
        <div class="list-group-item list-group-item-success" style="margin-top: 0.8rem" >
            <div class="col-12 row justify-content-between ">
                <div >
                    <span>2017-6-3</span> <small>${cfg.strCommentor}</small> <span>时代封俊</span>
                </div>
                <div >
                    <input type="button" class="btn btn-danger btn-sm" onclick="deleteComment(this)" value="${cfg.strDelete}">
                    <textarea hidden></textarea>
                </div>
            </div>
        </div>
        <div class="list-group-item" >
            <div class="col-12">
                <div>pigs李淑女坊三级分类时代封俊</div>
            </div>
        </div>
    </div>
    <div  class="list-group" style="margin-top: 2rem">
        <div  class="list-group-item list-group-item-success">
        ${cfg.strAllComments}
        </div>
    </div>
    <!-- 评论详情 -->
    <div id="listGroupComment" class="list-group" >
        
    </div>  <!-- 评论详情 -->
    <div  class="list-group" style="margin-top: 0.8rem">
        <div class="list-group-item"> <!-- 分页 -->
            <div class="blogmetaList col-md-12 justify-content-between">
                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <li class="page-item">
                            <a class="page-link" href="javascript:loadCommentLastPage()" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item">
                            <form class="form-inline">
                                <input  type="number" style="width: 80px" class="form-control" id="inputJumpCommentPage" value="0" placeholder="0">
                                /<span id="spanPageCount"></span> &nbsp;
                                <button type="button" class="btn btn-primary " onclick="loadCommentJumpPage()">Go</button>
                            </form>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="javascript:loadCommentNextPage()" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div> <!-- 分页 -->
    </div><!--查看评论-->
    
</div> <!-- /container -->



<#include "comm/navbar.bottom.html.ftl">
<#include "comm/msgtip.html.ftl">
<#include "comm/confirm.win.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/marked.min.js"></script>
<script src="${cfg.rootUrl}/js/blogdetail.js"></script>
</body>
</html>