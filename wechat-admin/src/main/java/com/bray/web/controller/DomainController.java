package com.bray.web.controller;

import com.bray.config.WebConst;
import com.bray.dto.ConstatFinal;
import com.bray.dto.DomainType;
import com.bray.model.Bo.PrimarySubDomain;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.DomainModelVo;
import com.bray.model.Vo.SubDomainModelVo;
import com.bray.service.IDomainAdminService;
import com.bray.service.IDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @Author:wuzhiyuan
 * @Description: 域名管理控制器
 * @Date: Created in 12:25 2018/8/31
 * @Modified By:
 */
@Slf4j
@Controller
@RequestMapping("/domain")
public class DomainController {

    @Autowired
    private IDomainService domainService;
    @Autowired
    private IDomainAdminService iDomainAdminService;
    /**
     * 域名跳转
     * @return
     */
    @RequestMapping("/show")
    public String doamin(Model model) {
        //查询所有有效域名
        List<PrimarySubDomain> primarySubDomainList =
                (List<PrimarySubDomain>) domainService.queryAllEffectiveDomain(ConstatFinal.QUERY_ADMIN);
        model.addAttribute(WebConst.ALL_DOMAIN,primarySubDomainList);
        return "domain/domain";
    }
    /**
     * 域名添加跳转
     * @return
     */
    @RequestMapping("/add")
    public String addjump() {
        return "domain/domain-add";
    }
    /**
     * 主域名添加
     * @return
     */
    @RequestMapping("/domain-add")
    @ResponseBody
    public RestResponseBo add(DomainModelVo domainModelVo) {
        iDomainAdminService.domainAdd(domainModelVo);
        return RestResponseBo.ok();
    }

    /**
     * 子域名添加跳转
     * @return
     */
    @RequestMapping("/sub-add")
    public String subAddjump() {
        return "domain/subdomain-add";
    }
    /**
     *  子域名添加
     * @return
     */
    @ResponseBody
    @RequestMapping("/subdomain-add")
    public RestResponseBo subAdd(SubDomainModelVo subDomainModelVo) {
        iDomainAdminService.subdomainAdd(subDomainModelVo);
        return RestResponseBo.ok();
    }
    /**
     *  域名删除
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public RestResponseBo delete(int id,String type) {
        if(DomainType.wydomain.name().equals(type)) {
            iDomainAdminService.deleteDomain(id);
        } else if (DomainType.wysubdomain.name().equals(type)) {
            iDomainAdminService.deleteSubdomain(id);
        }
        return RestResponseBo.ok();
    }
    /**
     * 更新线上域名设置
     */
    @RequestMapping("/update-prod")
    @ResponseBody
    public RestResponseBo updateProd(){
        iDomainAdminService.updateProd();
        return RestResponseBo.ok();
    }
    /**
     * 更新域名状态
     */
    @RequestMapping("/update-status")
    @ResponseBody
    public RestResponseBo updateStatus(int status,int domainId) {
        iDomainAdminService.updateStatus(status,domainId);
        return RestResponseBo.ok();
    }
}
