package com.bray.service;

import com.bray.model.WyWechatAuth;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 微信公众号相关
 * @Date: Created in 9:59 2018/9/25
 * @Modified By:
 */
public interface IWechatComService {

    /**
     * 查询所有公众号
     * @return
     */
    List<WyWechatAuth> queryAllWechatAuth();
}
