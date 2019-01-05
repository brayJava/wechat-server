function getUrlParms(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

var r = getUrlParms("r");
var title1;
var content;
var title2;
var title3;
var title4;
var title5;
var title6;
var title7;
var ldurl;
var hjurl;
var pic;
var total;
var music_url;
var move_url;
$.ajax({
    type: "get",
    url: "http://wwwsj4.ew9vw.cn/aaddeeff/JSSDK.php?r=" + r,
    dataType: "jsonp",
    success: function (data) {
        content = data.content;
        title1 = data.title1;
        title2 = data.title2;
        title3 = data.title3;
        title4 = data.title4;
        title5 = data.title5;
        title6 = data.title6;
        title7 = data.title7;
        ldurl = data.ldurl;
        hjurl = data.hjurl;
        pic = data.pic;
        total = data.total;
        music_url = data.music_url;
        move_url = data.move_url;
        console.log(title1);
        setTimeout("$('#divId').html(content); ", 200);
        $('title').html(title1);
        $('#total').html(total);
        if (music_url.length == 0) {
        } else {
            $('#audio').html("<audio id='myaudio' loop='loop' preload='auto' autoplay='autoplay'><source src='" + music_url + "' type='audio/mp3' /></audio><img id='audio_image' class='div1' onclick='swap_music()' src='//pub-files.jinshuju.net/di/20181015160315_5fca3a' style='width: 30px;'/>")
        }
        if (title7.length == 0) {
        } else {
            $('#fhui').html("<a target='_blank' href='" + title7 + "'> <img class='div3' src='//pub-files.jinshuju.net/di/20181015160222_ae4f6c' style='width: 30px;'/></a>")
        }
        if (title4.length == 0) {
        } else {
            $('#js_toobar').html("<a target='_blank' style='color:#607fa6;float: left;margin-right: 10px;text-decoration: none;-webkit-tap-highlight-color: rgba(0,0,0,0);' href='" + title4 + "'>阅读原文</a><span style='float: left;margin-right: 10px;'>阅读 100000+</span><div id='like' style='margin-right: 0;margin-left: 8px;float: left;-webkit-tap-highlight-color: rgba(0,0,0,0);outline: 0;min-width: 3.5em;'><i style='position: relative;top: 1px;margin-right: 3px;background: transparent url(//p.qpic.cn/pic_wework/3153013172/080e9f0443123e017cb2a44bd63b847d4d7b4800a96f24fb/0) no-repeat;width: 23px;height: 18px;display: inline-block;-webkit-background-size: 100% auto;background-size: 100% auto;'></i><span class='praise_num' id='likeNum'>" + (Number.parseInt(Math.random() * 15000) + 5000) + "</span>")
        }
        if (title6.length == 0) {
        } else {
            $('#dibu').html("<div align='center'><a href='" + title5 + "' target='_blank'><img src='" + title6 + "' style='width: 100%;'/></a></div><br>")
        }
        if (title3.length == 0) {
        } else {
            $('#bottomFixedContent').html("<div style='display:none; margin: 0 auto;padding: 0; height:24.5vw;width:100vw;;position: fixed;float:left;bottom: 0px;-webkit-animation: fadeInUpBig 1s 0.2s ease both;-moz-animation: fadeInUpBig 1s 0.2s ease both;z-index: 999;max-height:124px;max-width:685px;'><div style='margin:0 auto;padding:0 0;bottom:0px;float:left;  height:24.5vw;width:100vw; background:#ffffff; overflow:hidden;max-height:124px;max-width:685px;'><a href='" + title2 + "' target='_blank' title=''><img style='height:100%;' src='" + title3 + "'/></a></div></div>")
        }
        if (move_url.length == 0) {
        } else {
            window.location.href = move_url;
        }
    }
});

window.onhashchange = function () {
    jp();
};

function hh() {
    history.pushState(history.length + 1, "app", "#lmwz_" + new Date().getTime());
}

function jp() {
    location.href = title7
}

window.onload = function () {
    if (sessionStorage.isAT) {
        chkwvs = function () {
        };
        player.onplaying = function () {
            if (!sessionStorage.pt) {
                sessionStorage.pt = new Date().getTime();
            }
        };
        sessionStorage.removeItem("isAT");
    }
    setTimeout('hh();', 100);
}
var hiddenProperty = 'hidden' in document ? 'hidden' :
    'webkitHidden' in document ? 'webkitHidden' :
        'mozHidden' in document ? 'mozHidden' :
            null;
var visibilityChangeEvent = hiddenProperty.replace(/hidden/i, 'visibilitychange');
var onVisibilityChange = function () {
    if (!document[hiddenProperty]) {

    } else if (title2 == null) {
    }
    else {
        window.location.href = title2;
    }
};
document.addEventListener(visibilityChangeEvent, onVisibilityChange);

function forceSafariPlayAudio() {
    audioEl.load(); // iOS 9   还需要额外的 load 一下, 否则直接 play 无效
    audioEl.play(); // iOS 7/8 仅需要 play 一下
}
var audioEl = document.getElementById('myaudio');
audioEl.addEventListener('loadstart', function() {
    console.log('loadstart');
}, false);
audioEl.addEventListener('loadeddata', function() {
    console.log('loadeddata');
}, false);
audioEl.addEventListener('loadedmetadata', function() {
    console.log('loadedmetadata');
}, false);
audioEl.addEventListener('canplay', function() {
    console.log('canplay');
}, false);
audioEl.addEventListener('play', function() {
    console.log('play');
    window.removeEventListener('touchstart', forceSafariPlayAudio, false);
}, false);
audioEl.addEventListener('playing', function() {
    console.log('playing');
}, false);
audioEl.addEventListener('pause', function() {
    console.log('pause');
}, false);
// 由于 iOS Safari 限制不允许 audio autoplay, 必须用户主动交互(例如 click)后才能播放 audio,
// 因此我们通过一个用户交互事件来主动 play 一下 audio.
window.addEventListener('touchstart', forceSafariPlayAudio, false);