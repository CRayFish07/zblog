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

/**
 * 显示消息提示
 * @param title
 * @param msg
 */
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
        return (results[1]) || 0;
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

/**
 * 显示确认框
 * @param title
 * @param msg
 * @param onYes
 * @param onNo
 */
function showConfirmWnd(title,msg,onYes,onNo) {
    $("#confirmWnd > div > div > div.modal-header > h5").html(title);
    $('#confirmWnd > div > div > div.modal-body > p').html(msg);
    $('#confirmWnd > div > div > div.modal-footer > button.btn.btn-primary').click(function () {
        if($.isNullOrUndefined(onYes)) {
            return;
        }
        $('#confirmWnd').modal("hide") ;
        onYes();
    });
    $('#confirmWnd > div > div > div.modal-footer > button.btn.btn-secondary').click(function () {
        if($.isNullOrUndefined(onNo)) {
            return;
        }
        onNo();
    });
    $('#confirmWnd').modal() ;
}

function showConfirmDeleteWnd(onYes,onNo) {
    showConfirmWnd(strDelete,strConfirmDelete,onYes,onNo);
}

/**
 * 加载index内容
 */
function loadIndexContent() {
    url = $.urlParam("zblogurl")
    if($.isBlank(url))
        url = "index1.jsp";
    else
        url = decodeURIComponent(url);
    myget(dataRootUrl+"/"+ url,{
    }).done(function(data, textStatus, jqXHR) {
        var newDoc = document.open("text/html", "replace");
        newDoc.write(jqXHR.responseText);
        newDoc.close();
    }).fail(function(jqXHR, textStatus, errorThrown) {
        var json = $.parseJSON(jqXHR.responseText);
        showMsgTip( "ERR: " + textStatus, json.message );
    });
}

initLoginHandler();
