// function getUrlParms(name) {
//     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
//     var r = window.location.search.substr(1).match(reg);
//     if (r != null)
//         return unescape(r[2]);
//     return null;
// }
//
// var cid = getUrlParms("cid");
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
window.PREFIX_URL = "http://" + window.location.host + "/";
content = article.contentHtml;
title1 = article.wyArticle.title;
title2 = '';
title3 = '';
title4 = article.wyArticle.readOriginalUrl;
title5 = '';
title6 = '';
title7 = article.wyArticle.gobackUrl;
title8 = article.wyArticle.shareSuccessFailUrl;
ldurl = article.wyArticle.noShareDomain;
hjurl = '';
pic = '';
total = article.wyArticle.statistical;
music_url = article.wyArticle.bgmUrl;
move_url = article.wyArticle.dataTransferUrl;
console.log(title1);
setTimeout("$('#divId').html(content); ", 200);
$('title').html(title1);
$('#total').html("");
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
//第一次自动播放音乐
// document.getElementById('myaudio').play();
window.onhashchange = function () {
    jp();
};

function hh() {
    history.pushState(history.length + 1, "app", "#lmwz_" + new Date().getTime());
    // let ua = navigator.userAgent.toLowerCase();
    // if(ua.match(/MicroMessenger/i) == "micromessenger") {
    //     history.replaceState(history.state, null, window.location.pathname);
    //     let url = window.location.href;
    // }
}

function jp() {
    location.href = title7;
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
document.addEventListener("WeixinJSBridgeReady", function () {
    document.getElementById('myaudio').play();
}, false);
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
var signatureJson = {};
$.ajax({
    url: window.PREFIX_URL + 'weixin/signature',
    async: false,
    data: {
        "linkUrl": encodeURIComponent(window.location.href.split('#')[0]),
        "domainVerfiy": article.domainUrl
    },
    dataType: "json",
    type: 'post',
    success: function (data) {
        signatureJson = data;
    }
});

if(article.wyArticle.forcedShare) {
    wx.config({
        debug: true,
        appId: signatureJson.theAppId,
        timestamp: signatureJson.timestamp,
        nonceStr: signatureJson.noncestr,
        signature: signatureJson.signature,
        jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'error']
    });

    wx.ready(function () {
        wx.onMenuShareTimeline({
            title: article.wyArticle.shareTitle,
            link: article.shareUrl,
            imgUrl: article.wyArticle.shareImgUrl,
            success: function () {
                window.location.href = title8;
            },
            cancel: function () {
                window.location.href = title8;// 用户取消分享后执行的回调函数
            }
        });
        wx.onMenuShareAppMessage({
            title: article.wyArticle.shareTitle,
            desc: article.wyArticle.shareDescribe,
            link: base64Decode(share_url),
            imgUrl: article.wyArticle.shareImgUrl,
            type: '',
            dataUrl: '',
            success: function () {
                window.location.href = title8;
            },
            cancel: function () {
                window.location.href = title8;// 用户取消分享后执行的回调函数
            }
        });
    })
}



// if(article.wyArticle.forcedShare) {
//     $.ajax({
//         url: window.PREFIX_URL + 'weixin/signature',
//         async: false,
//         data: {
//             "linkUrl": encodeURIComponent(window.location.href.split('#')[0]),
//             "domainVerfiy": article.domainUrl
//         },
//         dataType: "json",
//         type: 'post',
//         success: function (data) {
//             wx.config({
//                 debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
//                 appId: data.theAppId, // 必填，公众号的唯一标识
//                 timestamp:data.timestamp, // 必填，生成签名的时间戳
//                 nonceStr: data.noncestr, // 必填，生成签名的随机串
//                 signature: data.signature,// 必填，签名
//                 jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage'], // 必填，需要使用的JS接口列表
//             });
//             var num = 0;
//             var nums =0;
//             shareData = {
//                 title: article.wyArticle.shareTitle, // 分享标题
//                 desc: article.wyArticle.shareDescribe, // 分享描述
//                 link: article.shareUrl, // 分享链接
//                 imgUrl: article.wyArticle.shareImgUrl, // 分享图片
//                 type: '', // 分享类型,music、video或link，不填默认为link
//                 dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
//                 success: function () {
//                     window.location.href = title8;
//                 },
//                 cancel: function () {
//                     window.location.href = title8;// 用户取消分享后执行的回调函数
//                 }
//             }
//             wx.ready(function(){
//                 // 分享到朋友圈
//                 wx.onMenuShareTimeline(shareData);
//                 // 分享给好友
//                 wx.onMenuShareAppMessage(shareData);
//             });
//         }
//     });
// }