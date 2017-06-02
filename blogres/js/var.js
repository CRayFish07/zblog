var dataServerIp = "127.0.0.1";
var dataServerPort = 8080;

var dataRootUrl = "http://127.0.0.1:8080";

function showMsgTip(title,msg) {
    $("#msgtipModalTitle").html(title);
    $("#msgtipModalContent").html(msg);
    $("#msgtipModal").modal();
}