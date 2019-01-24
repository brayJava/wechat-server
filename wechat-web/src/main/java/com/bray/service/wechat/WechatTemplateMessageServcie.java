package com.bray.service.wechat;

import com.bray.service.wechat.bean.OrderTemplateKeyParam;
import com.bray.service.wechat.bean.TemplateParam;
/**
 * @author:wuzhiyuan
 * @description: 微信模板接口
 * @date: Created in 17:56 2018/5/28
 * @modified By:
 */
public interface WechatTemplateMessageServcie {

    /**
     * @param orderTemplateKeyParam  模板参数值
     */
    void sendTemplateMessage(OrderTemplateKeyParam orderTemplateKeyParam);
}
