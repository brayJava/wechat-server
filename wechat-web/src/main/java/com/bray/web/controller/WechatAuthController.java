package com.bray.web.controller;

import com.bray.model.WyArticle;
import com.bray.service.impl.WechatAuthServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
/**
 * @Author:wuzhiyuan
 * @Description: 微信授权接口
 * @Date: Created in 9:51 2018/8/15
 * @Modified By:
 */
@RequestMapping("/weixin")
@Controller
public class WechatAuthController {

    @Resource
    private WechatAuthServiceImpl wechatAuthService;

    @PostMapping("/signature")
    @ResponseBody
    public String signature(String linkUrl, String domainVerfiy
            , HttpServletRequest request) {
        String currentDomain = "";
        try {
            linkUrl = URLDecoder.decode(linkUrl, "UTF-8");
            //获取当前域名
            currentDomain = request.getServerName();
            currentDomain = currentDomain.substring(currentDomain.indexOf(".") + 1, currentDomain.length());
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String signature = wechatAuthService.signature(linkUrl,domainVerfiy,currentDomain);
        return signature;
    }
}
