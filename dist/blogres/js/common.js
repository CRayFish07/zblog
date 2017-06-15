// function setLoginCookie(sid) {
//     $.cookie("sid",sid);
// }
//
// function getLoginSid() {
//     return $.cookie("sid");
// }
//
// function addSidForUrl(url) {
//     var sid = getLoginSid();
//     if($.isBlank(sid))
//         return url;
//
//     if(url.indexOf("?")>=0) {
//         url = url+"&sid="+sid;
//     } else{
//         url = url+"?sid="+sid;
//     }
//     return url;
// }

function mypost(url, data) {
   // url = addSidForUrl(url);
    return $.ajax({
        type: "POST",
        url: url,
        xhrFields: { withCredentials: true },
        crossDomain: true,
        data: data,
    });
}
function myget(url, data) {
   // url = addSidForUrl(url);
    return $.ajax({
        type: "GET",
        url: url,
        xhrFields: { withCredentials: true },
        crossDomain: true,
        data: data,
    });
}


function showLogin() {
    $("#modalLogin").modal();
}

function initLoginHandler() {
    $("#buttonLoginSubmit").click(function (e) {
        $("#buttonLoginSubmit").prop("disabled",true);
        var password = $("#inputLoginPassword").val();
        mypost(dataRootUrl+"/login" , {
            password:        password
        }).done(function(data, textStatus, jqXHR) {
            //setLoginCookie(jqXHR.responseText);
            $("#modalLogin").modal("hide");
        }).fail(function(jqXHR, textStatus, errorThrown) {
            var json = $.parseJSON(jqXHR.responseText);
            showMsgTip( "ERR: " + textStatus, json.message );
        });
        $("#buttonLoginSubmit").prop("disabled",false);
    });
}

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

initLoginHandler();
