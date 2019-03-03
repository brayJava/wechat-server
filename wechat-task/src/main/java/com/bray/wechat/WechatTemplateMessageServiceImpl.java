package com.bray.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bray.mapper.WySafedomainMapper;
import com.bray.model.WySafedomain;
import com.bray.util.HttpUtil;
import com.bray.wechat.bean.*;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author:wuzhiyuan
 * @description:  微信公众号-模板信息接口
 * @date: Created in 0:14 2018/5/10
 * @modified By:
 */
@Service
public class WechatTemplateMessageServiceImpl implements WechatTemplateMessageServcie {

    @Resource
    private WySafedomainMapper wySafedomainMapper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * @param orderTemplateKeyParam  模板参数值
     */
    public void sendTemplateMessage(OrderTemplateKeyParam orderTemplateKeyParam) {
        //查询所有openid
        WySafedomain wySafedomain = wySafedomainMapper.selectByPrimaryKey(1);
        if(StringUtils.isEmpty(wySafedomain.getMail())){
            wySafedomain.setMail(WechatConstant.DEFULAT_TOUSER);
        }
        String[] wechats = wySafedomain.getMail().split(",");
        for(String openid: wechats) {
            sendWechatMsg(openid, orderTemplateKeyParam);
        }
    }

    @Override
    public void sendToUserTemplateMessage(String userid, OrderTemplateKeyParam orderTemplateKeyParam) {
        this.sendWechatMsg(userid,orderTemplateKeyParam);
    }
    /**
     * 发送下行通知消息
     * @param openid
     * @param orderTemplateKeyParam
     */
    private void sendWechatMsg(String openid, OrderTemplateKeyParam orderTemplateKeyParam) {

        DataInfo first = new DataInfo(orderTemplateKeyParam.getFirst(), "#c81623");
        DataInfo keyword1 = new DataInfo(orderTemplateKeyParam.getKeyword1(), "");
        DataInfo keyword2 = new DataInfo(orderTemplateKeyParam.getKeyword2(), "");
        DataInfo remark= new DataInfo(orderTemplateKeyParam.getRemark(), "#173177");

        OrderTemplateKeyword templateKeyword = new OrderTemplateKeyword(first, keyword1, keyword2,remark);
        //设置模板参数
        WechatTemplateMessage wechatTemplateMessage = new WechatTemplateMessage();
        wechatTemplateMessage.setTouser(openid);
        wechatTemplateMessage.setTemplate_id(WechatConstant.TEMPLATE_ID);
        wechatTemplateMessage.setUrl(WechatConstant.IM_WECHAT_URL);
        logger.info("下行通知连接地址：url>>>>{}",WechatConstant.IM_WECHAT_URL);
        wechatTemplateMessage.setData(templateKeyword);
        String jsonParam = String.valueOf(JSONObject.toJSON(wechatTemplateMessage));
        //获取access_token的值
        String url = HttpUtil.doGet(WechatConstant.ACCESS_TOKEN_URL);
        JSONObject jsonObject = JSON.parseObject(url);
        Object access_token = jsonObject.get("access_token");
        MediaType JSON = MediaType.parse(WechatConstant.YTPE);
        RequestBody body = RequestBody.create(JSON, jsonParam);
        Request request = new Request.Builder()
                .url(WechatConstant.URL+"?access_token="+String.valueOf(access_token))
                .post(body)
                .build();
        Call call = new OkHttpClient().newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}