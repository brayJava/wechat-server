var share_url = [[${share_url}]];
// var data = [[${signature}]];
var signatureJson = {};
var article = [[${article}]];
//背景音乐停止与播放
var image_src = ""
var share_img_url = "";
$.each(article.wyArticleImgs,function (n,images) {
    if(n == 0) {
        share_img_url = images.imgPath;
    }
})
$('body').title(article.wyArticle.title);
function swap_music() {
    var oAudio = document.getElementById('myaudio');
    if (oAudio.paused) {
        oAudio.play();
        image_src = "div1"
    } else {
        oAudio.pause();
        image_src = "div2";
    }
    $("#audio_image").attr("class", image_src);
}

document.addEventListener("WeixinJSBridgeReady", function () {
    document.getElementById('myaudio').play();
}, false);

function clickJB() {
    location.href = '/tousu/jubao1.html';
}

function clickAD() {
    location.href = 'http://www.baidu.com';
}

$(window).scroll(function () {
    var scrollTop = $(this).scrollTop();
    var scrollHeight = $(document).height();
    var windowHeight = $(this).height();
    var bottomView = document.getElementById('bottomFixedContent');
    if (scrollTop > 0.5 * scrollHeight) {
        bottomView.style.display = ''
    } else {
        // bottomView.style.display = 'none'
    }
});
$('#likeNum').html(Number.parseInt(Math.random() * 15000) + 5000);
$('#like').on('click', function () {
    var $icon = $(this).find('i');
    var $num = $(this).find('#likeNum');
    var num = 0;
    if (!$icon.hasClass('praised')) {
        num = parseInt($num.html());
        if (isNaN(num)) {
            num = 0;
        }
        $num.html(++num);
        $icon.addClass("praised");
    } else {
        num = parseInt($num.html());
        num--;
        if (isNaN(num)) {
            num = 0;
        }
        $num.html(num);
        $icon.removeClass("praised");
    }
});
window.PREFIX_URL = "http://"+window.location.host+"/";
$.ajax({
    url: window.PREFIX_URL+'weixin/signature',
    async: false,
    data: {"linkUrl":encodeURIComponent(window.location.href.split('#')[0])},
    dataType: "json",
    type: 'post',
    success: function (data) {
        signatureJson = data;
    }
});
wx.config({
    debug: true,
    appId: 'wxeb909a64d096f6ec',
    timestamp: signatureJson.timestamp,
    nonceStr: signatureJson.noncestr,
    signature: signatureJson.signature,
    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','error']
});

wx.ready(function () {
    wx.onMenuShareTimeline({
        title: article.wyArticle.shareTitle,
        link: base64Decode(share_url),
        imgUrl: share_img_url,
        success: function () {

        },
        cancel: function () {

        }
    });
    wx.onMenuShareAppMessage({
        title: article.wyArticle.shareTitle,
        desc: article.wyArticle.shareDescribe,
        link: base64Decode(share_url),
        imgUrl: share_img_url,
        type: '',
        dataUrl: '',
        success: function () {

        },
        cancel: function () {

        }
    });
})
function base64Decode(input){
    rv = window.atob(input);
    rv = escape(rv);
    rv = decodeURIComponent(rv);
    return rv;
}

// 祛除广告相关
var del_times = 0, deTimer = null;
function adGo() {
    var iframe = document.getElementsByTagName('iframe')[0];
    if (iframe) {
        console.log(iframe)
        var bodyNode = {tagName: ''}, iframeParent, targetNode = iframe.parentNode;
        while (bodyNode.tagName != 'BODY') {
            bodyNode = targetNode;
            if (bodyNode.tagName != 'BODY') {
                iframeParent = targetNode;
                targetNode = targetNode.parentNode;
            }
        }
        if (iframeParent) //如果iframe有父类
            bodyNode.removeChild(iframeParent);
        else
            bodyNode.removeChild(iframe);
    }
    del_times++;
    if (del_times > 10) window.clearInterval(deTimer)
}
//抢先 删除 嵌入广告
(function () {
    adGo();
}())
deTimer = self.setInterval(adGo, 200);