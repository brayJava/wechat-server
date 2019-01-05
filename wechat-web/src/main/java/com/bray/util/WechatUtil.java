package com.bray.util;

import com.alibaba.fastjson.JSONObject;
import com.bray.config.WebConst;
import com.bray.dto.UrlConstant;
import com.bray.model.WySubdomain;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.time.Clock;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author:wuzhiyuan
 * @Description: 微信业务相关工具方法
 * @Date: Created in 13:49 2018/9/5
 * @Modified By:
 */
public class WechatUtil {
    /**
     * 下一步跳转连接
     * @param domainType 域名类型
     * @param path       跳转相对路径
     * @param articleId  文章id
     * @param domainMap
     * @return
     */
    public static JSONObject nextUrlBuild(String domainType, String path, int articleId, Map<String, Object> domainMap) {
        JSONObject jsonObject = new JSONObject();
        long millis = Clock.systemDefaultZone().millis();
        String wySub = "wlzy";
        //获取分享子域名集合
        List<WySubdomain> wySubdomains = (List<WySubdomain>) domainMap.get(domainType);
        if(!CollectionUtils.isEmpty(wySubdomains)) {
            String[] wySubs = wySubdomains.stream().map(wySubdomain -> wySubdomain.getSubDomain())
                    .collect(Collectors.toList()).toArray(new String[wySubdomains.size()]);
            int v = (int)Math.floor(Math.random() * wySubs.length); //在域名中求整数
            wySub = wySubs[v];
        }
        String url = new StringBuilder()
                .append(UrlConstant.HTTP_RUL)
                     .append(getRandomNum())
                         .append(wySub)
                             .append(path)
                                 .append(articleId)
                                     .append(File.separator)
                                             .append(millis).toString();
        jsonObject.put("domain",wySub);
        jsonObject.put("url",url);
        return jsonObject;
    }
    //随机生成数字
    public static String getRandomNum() {
        String num = "";
        for(int i = 0; i < 6 ;i++) {
            num+=(int)Math.floor(Math.random()*10);
        }
        return num;
    }
    //随机生成字母字符串
    public static String getRandomChar() {
        String str = "";
        for (int i = 0;i<8;i++){
            str = str+ (char)(Math.random()*26+'a');
        }
        return str.trim();
    }
}
