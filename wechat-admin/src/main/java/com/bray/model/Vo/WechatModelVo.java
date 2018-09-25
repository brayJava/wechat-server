package com.bray.model.Vo;

import lombok.Data;

/**
 * @Author:wuzhiyuan
 * @Description: 微信公众号相关
 * @Date: Created in 14:37 2018/9/25
 * @Modified By:
 */
@Data
public class WechatModelVo {

    private int id;

    private String weixinName;

    private String weixinId;

    private String weixinSecret;

    private String bindDomains;

    private String debug;

}
