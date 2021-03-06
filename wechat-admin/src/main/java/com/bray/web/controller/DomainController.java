package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.config.WebConst;
import com.bray.dto.ConstatFinal;
import com.bray.dto.DomainType;
import com.bray.model.Bo.PrimarySubDomain;
import com.bray.model.Bo.RestResponseBo;
import com.bray.model.Vo.DomainModelVo;
import com.bray.model.Vo.SubDomainModelVo;
import com.bray.model.WySafedomain;
import com.bray.service.IArticleService;
import com.bray.service.IDomainAdminService;
import com.bray.service.IDomainService;
import com.bray.service.impl.DomainServiceImpl;
import com.bray.util.TStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Resource
    private RedisPoolCache redisObj;
    /**
     * 域名跳转
     * @return
     */
    @RequestMapping("/show")
    public String doamin(Model model) {
        //查询所有有效域名
        // List<PrimarySubDomain> primarySubDomainList =
        //         (List<PrimarySubDomain>) domainService.queryAllEffectiveDomain(ConstatFinal.QUERY_ADMIN,"");
        HashMap<String,List<PrimarySubDomain>> primarySubDomainMap =
                (HashMap<String,List<PrimarySubDomain>>)domainService.queryPrimarySubDomainMap(ConstatFinal.QUERY_ADMIN, 1);
        model.addAttribute(WebConst.ALL_MAP_DOMAIN,primarySubDomainMap);
        // model.addAttribute(WebConst.ALL_DOMAIN,primarySubDomainList);
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
     *  同步线上对于文章id域名
     * @return
     */
    @ResponseBody
    @RequestMapping("/refresh-prod")
    public RestResponseBo refreshProd(int id) {
        // iDomainAdminService.refreshDomainByArticleId(id);
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
    /**
     * 访问安全域名
     */
    @RequestMapping("/domain-safe")
    public String domainSafe(Model model) {
        WySafedomain wySafedomain = iDomainAdminService.querySafeDomain();
        String checkDomains = String.valueOf(redisObj.getRedisValueByKey("checkDomains"));
        model.addAttribute("wySafedomain",wySafedomain);
        model.addAttribute("checkDomains",checkDomains);
        return "domain/domain-safe";
    }

    /**
     * 修改安全域名
     */
    @RequestMapping("/domain-safe-update")
    @ResponseBody
    public RestResponseBo domainSafeUpdate(String safedomains,String mail,String checkDomain) {
        String dealStr = TStringUtil.dealStr(safedomains);
        String mailStr = TStringUtil.dealStr(mail);
        String checkDomainStr = TStringUtil.dealStr(checkDomain);
        redisObj.deleteDataOfRedis("safeDomains");
        redisObj.updateDataOfRedis("checkDomains",checkDomainStr);
        iDomainAdminService.updateSafeDomain(dealStr,mailStr);
        return RestResponseBo.ok();
    }

}
