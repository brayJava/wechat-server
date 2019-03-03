package com.bray.wechat;
import com.bray.wechat.bean.OrderTemplateKeyParam;

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

    /**
     * @param orderTemplateKeyParam  模板参数值
     * @param userid  用户id
     */
    void sendToUserTemplateMessage(String userid,OrderTemplateKeyParam orderTemplateKeyParam);
}
