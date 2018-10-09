package com.bray.web.controller;

import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.WechatModelVo;
import com.bray.model.WyWechatAuth;
import com.bray.service.IWechatAdminService;
import com.bray.service.IWechatComService;
import com.bray.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.util.Collection;
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
    @Value("${web.upload-path}")
    private String serverRootFile;
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

    /**
     * 文件上传界面
     * @return
     */
    @RequestMapping("/upload-html")
    public String uploadHtml() {

        return "wechat/wechat-file";
    }
    /**
     * 公众号添加或修改
     * 处理文件上传
     * @return
     */
    @PostMapping("/file-upload")
    public String fileUpload(@RequestParam("file") MultipartFile file,
                                     HttpServletRequest request,Model model) {
        try {
            FileUtil.uploadFile(file.getBytes(),serverRootFile,file.getOriginalFilename());
            model.addAttribute("code","success");
        } catch (Exception e) {
            model.addAttribute("code","fail");
            e.printStackTrace();
        }
        return "wechat/wechat-file";
    }
}
