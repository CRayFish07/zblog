var dataServerIp = "127.0.0.1";
var dataServerPort = 8080;

var dataRootUrl = "http://127.0.0.1:8080";
var uploadRootUrl = "http://127.0.0.1:8080"+"/upload";
var strPleaseDragUploadingFilesHere = "把要上传的文件拖到这里";

function showMsgTip(title,msg) {
    $("#msgtipModalTitle").html(title);
    $("#msgtipModalContent").html(msg);
    $("#msgtipModal").modal();
}

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
        return null;
    } else {
        return decodeURI(results[1]) || 0;
    }
}


$.isNullOrUndefined = function( o ) {
    if (typeof(o) == "undefined")
        return true;
    if(o==null)
        return true;
    return false;
}

$.isBlank = function(str) {
    if($.isNullOrUndefined(str))
        return true;
    if($.trim(str).length<=0)
        return true;
    return false
}


function replaceAll(str, find, replace) {
    return str.replace(new RegExp(find, 'g'), replace);
}

function prepareMarkdownText(str) {
    return replaceAll(str,"blogfile://",uploadRootUrl);
}

function setMarked() {
    marked.setOptions({
        breaks: true
    });
}