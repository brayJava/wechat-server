//xxxx

var doc = $(document);


var _touches_point1=0;var _touches_point2=0;
addEventListener("touchstart",
    function(e) {
        _touches_point1 = e.touches[0].pageY;
    });
addEventListener("touchmove",
    function(e) {

        _touches_point2 = e.touches[0].pageY;
        if(doc.scrollTop()<=0 && _touches_point1<_touches_point2)
        {
            e.preventDefault();
            if( $('#_domain_display').length <=0 )
            {
                $('body').prepend('<div id="_domain_display" style="text-align:center;background-color:#272b2e;color:#65696c;height:0px;padding-top:15px;line-height:26px;font-size:12px;overflow:hidden;"><p>网页由 news.qq.com 提供</p><p>QQ浏览器X5内核提供技术支持</p></div>');
            }
            $('#_domain_display').height((_touches_point2-_touches_point1));

        }
    });

addEventListener("touchend",
    function(e) {
        $('#_domain_display').slideUp('normal' , function(){
            $('#_domain_display').remove();
        });
    });