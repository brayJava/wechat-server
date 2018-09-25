package com.bray.service.impl;

import com.bray.dto.EffectiveType;
import com.bray.mapper.WyWechatAuthMapper;
import com.bray.model.Vo.WechatModelVo;
import com.bray.model.WyWechatAuth;
import com.bray.service.IWechatAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;
/**
 * @Author:wuzhiyuan
 * @Description:
 * @Date: Created in 15:55 2018/9/25
 * @Modified By:
 */
@Service
@Transactional
@Slf4j
public class WechatServiceImpl implements IWechatAdminService {
    @Resource
    private WyWechatAuthMapper wyWechatAuthMapper;
    /**
     * 添加或修改公众号
     * @param obj
     */
    @Override
    public void wechatAddOrUpdate(Object obj) {
        WechatModelVo wechatModelVo = (WechatModelVo) obj;
        if(!Objects.isNull(wechatModelVo)) {
            WyWechatAuth wyWechatAuth = new WyWechatAuth();
            if(wechatModelVo.getId() == 0) {
                wyWechatAuth.setWeixinId(wechatModelVo.getWeixinId());
                wyWechatAuth.setWeixinSecret(wechatModelVo.getWeixinSecret());
                wyWechatAuth.setWeixinName(wechatModelVo.getWeixinName());
                wyWechatAuth.setBindDomains(wechatModelVo.getBindDomains());
                wyWechatAuth.setStatus(EffectiveType.EFFECTIVE_YES);
                wyWechatAuth.setCreateTime(new Date());
                wyWechatAuth.setUpdateTime(new Date());
                try {
                    wyWechatAuthMapper.insertSelective(wyWechatAuth);
                } catch (Exception e) {
                    log.error("----------添加公众号失败");
                    e.printStackTrace();
                }
            } else {
                wyWechatAuth.setId(wechatModelVo.getId());
                wyWechatAuth.setWeixinId(wechatModelVo.getWeixinId());
                wyWechatAuth.setWeixinSecret(wechatModelVo.getWeixinSecret());
                wyWechatAuth.setWeixinName(wechatModelVo.getWeixinName());
                wyWechatAuth.setBindDomains(wechatModelVo.getBindDomains());
                wyWechatAuth.setUpdateTime(new Date());
                try {
                    wyWechatAuthMapper.updateByPrimaryKeySelective(wyWechatAuth);
                } catch (Exception e) {
                    log.error("----------修改公众号失败");
                    e.printStackTrace();
                }
            }
        }
    }
}
