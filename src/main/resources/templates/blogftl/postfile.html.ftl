<!DOCTYPE html>
<html lang="${cfg.lang}">
<head>
<#include "comm/common.header.ftl">
    <link rel="stylesheet" href="${cfg.rootUrl}/css/style.min.css" />
    <link rel="stylesheet" href="${cfg.rootUrl}/css/basic.min.css" />
    <link rel="stylesheet" href="${cfg.rootUrl}/css/dropzone.min.css" />

    <title>${cfg.strPostFile} - ${cfg.blogName}</title>

</head>
<body>
<!-- Fixed navbar -->
<#include "comm/navbar.html.ftl">
<span class="glyphicon glyphicon-search"></span>
<div class="container">
    <div class="postFileButtons btn-group-vertical">
        <input id="buttonPostFile" type="button" class=" btn btn-primary" value="${cfg.strPostFile}">
        <input id="buttonMkdir" style="margin-top: 0.3rem" type="button" class=" btn btn-secondary" value="${cfg.strMkdir}">
        <input id="buttonDelete" style="margin-top: 0.3rem" type="button" class=" btn btn-danger" value="${cfg.strDelete}">
    </div>
    <!-- Main component for a primary marketing message or call to action -->
    <div class="postFileTree" id="treeContainer"></div>

</div> <!-- /container -->

<div  id="mkdirModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${cfg.strMkdir}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div class="form-group ">
                    <label for="inputMkdirPrent">${cfg.strParentDirectory}</label>
                    <input type="text" class="form-control" id="inputMkdirPrent">
                </div>
                <div class="form-group ">
                    <label for="inputMkdir">${cfg.strInputDirName}</label>
                    <input type="text" class="form-control" id="inputMkdir" placeholder="${cfg.strInputDirName}">
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">${cfg.strClose}</button>
                <input type="button" id="buttonMkdirSubmit" class="btn btn-primary" value="${cfg.strMkdir}">
            </div>
        </div>
    </div>
</div>  <!-- mkdirModal -->

<div  id="modalPostFile" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${cfg.strPostFile}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                <div>${cfg.strPostFileToDirectory} : <b><span id="spanPostDir"></span></b></div>
                <form id="formPostFile" action="${cfg.rootUrl}/file" class="dropzone">
                    <input id="inputPostDir" type="text" name="dir" hidden>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">${cfg.strClose}</button>
                <a class="btn btn-primary" href="postfile.html">${cfg.strFinishPostFile}</a>
            </div>
        </div>
    </div>
</div>  <!-- modalPostFile -->

<div  id="modalDelete" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">${cfg.strDelete}</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>

            <div class="modal-body">
                 确定删除: <span id="spanDeleteDir"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">${cfg.strClose}</button>
                <input type="button" id="buttonDeleteSubmit" class="btn btn-primary" value="${cfg.strDelete}"></input>
            </div>
        </div>
    </div>
</div>  <!-- deleteModal -->

<#include "comm/navbar.bottom.html.ftl">
<#include "comm/msgtip.html.ftl">
<#include "comm/common.js.ftl">
<script src="${cfg.rootUrl}/js/dropzone.min.js"></script>
<script src="${cfg.rootUrl}/js/jstree.min.js"></script>
<script src="${cfg.rootUrl}/js/postfile.js"></script>
</body>
</html>