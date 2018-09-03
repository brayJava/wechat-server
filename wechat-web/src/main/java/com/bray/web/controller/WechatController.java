package com.bray.web.controller;

import com.bray.config.WebConst;
import com.bray.dto.UrlConstant;
import com.bray.model.WyDomain;
import com.bray.model.WySubdomain;
import com.bray.service.impl.WechatAuthServiceImpl;
import com.bray.util.Base64Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 23:50 2018/8/24
 * @Modified By:
 */
@RequestMapping("/wechat")
@Controller
@Slf4j
public class WechatController {

    @Autowired
    private WechatAuthServiceImpl wechatAuthService;
    /**
     * 首页跳转
     */
    @RequestMapping("/jump/{temstamp}")
    String index(@PathVariable String temstamp, Model model) {
        //获取内容跳转链接
        String contentUrl = nextUrlBuild(temstamp,WebConst.SUB_COMMON_DOMAIN,UrlConstant.PATH_CONTENT_URL);
        //base64编码
        model.addAttribute(UrlConstant.CONTENT_URL, Base64Util.encode(contentUrl));
        log.info("----------内容链接为：{}",contentUrl);
        return "html/share";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/content/{timstamp}")
    String content(@PathVariable String timstamp,Model model) {
        String shareUrl = nextUrlBuild(timstamp,WebConst.SUB_SHARE_DOMAIN,UrlConstant.PATH_JUMP_RUL);
        //base64编码
        model.addAttribute(UrlConstant.SHARE_URL, Base64Util.encode(shareUrl));
        //放置签名信息
        String signature = wechatAuthService.signature("http://www.baidu.com");
        model.addAttribute("signature",signature);
        log.info("----------分享链接为：{}",shareUrl);
        return "html/content";
    }
    /**
     * 内容跳转
     */
    @RequestMapping("/story/{id}/{code}")
    String story(@PathVariable String id,@PathVariable String code) {
        return "order/story";
    }
    /**
     * 订单跳转
     */
    @RequestMapping("/order")
    String order() {
        return "order/order";
    }
    /**
     * 下一步跳转连接
     * @param timstamp   时间戳
     * @param domainType 域名类型
     * @param path       跳转相对路径
     * @return
     */
    private String nextUrlBuild(String timstamp,String domainType,String path) {
        long millis = Clock.systemDefaultZone().millis();
        //获取分享主域名
        List<WySubdomain> wySubdomains = (List<WySubdomain>) WebConst.domainMap.get(domainType);
        if(CollectionUtils.isEmpty(wySubdomains)) return "";
        String[] wySubs = wySubdomains.stream().map(wySubdomain -> wySubdomain.getSubDomain())
                .collect(Collectors.toList()).toArray(new String[wySubdomains.size()]);
        int v = (int)Math.random() * wySubs.length;
        return new StringBuilder().append(UrlConstant.HTTP_RUL).append(wySubs[v]).append(path).append(timstamp+millis).toString();
    }
}
