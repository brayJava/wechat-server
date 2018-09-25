package com.bray.service.impl;

import com.bray.dto.EffectiveType;
import com.bray.mapper.WyWechatAuthMapper;
import com.bray.model.WyWechatAuth;
import com.bray.model.WyWechatAuthExample;
import com.bray.service.IWechatComService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 查询所有公众号接口
 * @Date: Created in 10:03 2018/9/25
 * @Modified By:
 */
@Service
public class WechatComServiceImpl implements IWechatComService {
    @Resource
    private WyWechatAuthMapper wyWechatAuthMapper;
    /**
     * 查询所有公众号信息
     * @return
     */
    @Override
    public List<WyWechatAuth> queryAllWechatAuth() {
        WyWechatAuthExample wyWechatAuthExample = new WyWechatAuthExample();
        wyWechatAuthExample.createCriteria().andStatusEqualTo(EffectiveType.EFFECTIVE_YES);
        wyWechatAuthExample.setOrderByClause("update_time desc");
        List<WyWechatAuth> wyWechatAuths = wyWechatAuthMapper.selectByExample(wyWechatAuthExample);
        return wyWechatAuths;
    }
}
