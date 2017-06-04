function refreshFileTree() {
    $('#treeContainer').jstree(true).refresh();
}

function initDropzone() {
    var myDropzone = new Dropzone("#formPostFile",{
        init: function() {
            this.on("complete", function(file) {
                refreshFileTree();
            });
        },
        dictDefaultMessage : strPleaseDragUploadingFilesHere,
        uploadMultiple : false
    });
}

function showPostFileModal() {
    var selectedArr = $('#treeContainer').jstree(true).get_selected(true);
    var parentDir = "/"
    if(!$.isNullOrUndefined(selectedArr) && selectedArr.length>0) {
        parentDir = selectedArr[0].id+"/"
    }
    $("#spanPostDir").text(parentDir);
    $("#inputPostDir").val(parentDir);
    $("#modalPostFile").modal();
}

function showDeleteModal() {
    var selectedArr = $('#treeContainer').jstree(true).get_selected(true);
    if($.isNullOrUndefined(selectedArr) || selectedArr.length<=0) {
        return;
    }
    var dir = selectedArr[0].id
    $("#spanDeleteDir").text(dir);
    $("#modalDelete").modal();
}

function postDeleteDir() {
    var dir = $("#spanDeleteDir").text();

    $("#buttonDeleteSubmit").prop("disabled",true)
    var jqxhr = $.post(dataRootUrl+"/file/delete" , {
        dir:        dir
    }).done(function(data, textStatus, jqXHR) {
        refreshFileTree()
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
    $("#buttonDeleteSubmit").prop("disabled",false)
    $("#modalDelete").modal("hide");
}

function postMkdir() {
    var parentDir = $("#inputMkdirPrent").val();
    var dir = $("#inputMkdir").val();

    $("#buttonMkdirSubmit").prop("disabled",true)
    var jqxhr = $.post(dataRootUrl+"/file/mkdir" , {
        parentDir:  parentDir,
        dir:        dir
    }).done(function(data, textStatus, jqXHR) {
        $("#mkdirModal").modal("hide");
        refreshFileTree()
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    })
    $("#buttonMkdirSubmit").prop("disabled",false)
}

/**
 * 显示创建目录对话框
 */
function showMkdirModal() {
    var selectedArr = $('#treeContainer').jstree(true).get_selected(true);
    var parentDir = "/";
    if(!$.isNullOrUndefined(selectedArr) && selectedArr.length>0) {
        parentDir = selectedArr[0].id+"/"
    }
    $("#inputMkdirPrent").val(parentDir);
    $("#inputMkdir").val("");
    $("#mkdirModal").modal();
}

function initEventHandlers() {
    $("#buttonPostFile").click(function (event) {
        showPostFileModal();
    });
    $("#buttonMkdir").click(function (event) {
        showMkdirModal();
    });
    $("#buttonDelete").click(function (event) {
        showDeleteModal();
    });

    $("#buttonMkdirSubmit").click(function(e){
        //e.preventDefault();
        postMkdir();
    });

    $("#buttonDeleteSubmit").click(function (e) {
        postDeleteDir();
    });
}

function initFileTree() {
    $('#treeContainer').jstree({
        'core' : {
            'data' : {
                "url" : dataRootUrl+"/file/list",
                "data" : function (node) {
                    return { "dir" : node.id };
                }
            }
        }
    });
}


Dropzone.autoDiscover = false;
$(function() {
    $("#navPostFile").addClass("active");
    initFileTree();
    initDropzone();
    initEventHandlers();
});
