package com.bray.web.controller;

import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.WechatModelVo;
import com.bray.model.WyWechatAuth;
import com.bray.service.IWechatAdminService;
import com.bray.service.IWechatComService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 微信公众号相关
 * @Date: Created in 13:07 2018/9/23
 * @Modified By:
 */
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WechatAdminController {
    @Autowired
    private IWechatComService iWechatComService;
    @Autowired
    private IWechatAdminService iWechatAdminService;
    /**
     * 公众号展示
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String selectWechatAuth(Model model) {
        List<WyWechatAuth> wyWechatAuths = iWechatComService.queryAllWechatAuth();
        model.addAttribute("wyWechatAuths",wyWechatAuths);
        return "wechat/wechat-list";
    }
    @RequestMapping("/jump-add")
    public String jumpAdd() {
        return "wechat/wechat-add";
    }
    /**
     * 公众号添加或修改
     * @param wechatModelVo
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public RestResponseBo addWechatAuth(WechatModelVo wechatModelVo) {
        iWechatAdminService.wechatAddOrUpdate(wechatModelVo);
        log.info("-------{}",wechatModelVo);
        return RestResponseBo.ok();
    }

}
