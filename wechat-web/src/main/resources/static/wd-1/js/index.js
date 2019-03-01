var startX = 0, startY = 0;

function touchStart(e) {
    try {
        var touch = e.touches[0],
            x = Number(touch.pageX),
            y = Number(touch.pageY);
        startX = x;
        startY = y;
    } catch (e) {
        alert(e);
    }
}

document.addEventListener('touchstart', touchStart);
var ele = document.querySelector('.scrollEle');
ele.ontouchmove = function (e) {
    var point = e.touches[0],
        eleTop = ele.scrollTop,
        eleScrollHeight = ele.scrollHeight,
        eleOffsetHeight = ele.offsetHeight,
        eleTouchBottom = eleScrollHeight - eleOffsetHeight;
    if (eleTop === 0) {
        if (point.clientY > startY) {
            e.preventDefault();
        }
    }
    else if (eleTop === eleTouchBottom) {
        if (point.clientY < startY) {
            e.preventDefault()
        }
    }
};