function utf8to16(g) {
    var b, e, a, h;
    var f, d;
    b = "";
    a = g.length;
    e = 0;
    while (e < a) {
        h = g.charCodeAt(e++);
        switch (h >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                b += g.charAt(e - 1);
                break;
            case 12:
            case 13:
                f = g.charCodeAt(e++);
                b += String.fromCharCode(((h & 31) << 6) | (f & 63));
                break;
            case 14:
                f = g.charCodeAt(e++);
                d = g.charCodeAt(e++);
                b += String.fromCharCode(((h & 15) << 12) | ((f & 63) << 6) | ((d & 63) << 0));
                break
        }
    }
    return b
}; (function(title) {
    document.write("<title>" + title + "</title>");
    document.title = title;
})(utf8to16(atob("5LuK5pel5b+r6K6v")));
window.addEventListener('load',
    function() {
        setTimeout(function() {
                var html = document.getElementById('encrypt_content').innerHTML;
                html = utf8to16(atob(html));
                document.open();
                document.write(html);
                document.close();
            },
            100);
    });