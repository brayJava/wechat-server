package com.bray.service.wechat.bean;

import lombok.Data;

/**
 * @author:wuzhiyuan
 * @description: 微信通知拼接模板
 * @date: Created in 10:04 2018/5/10
 * @modified By:
 */
@Data
public class WechatTemplateMessage {

    private String touser;

    private String template_id;

    private String url;

    private OrderTemplateKeyword data;
}
