package com.bray.web.controller;

import com.bray.aop.cache.RedisPoolCache;
import com.bray.dto.OrderLogType;
import com.bray.service.IOrderWebService;
import com.bray.util.HttpRequestDeviceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:wuzhiyuan
 * @Description: 订单相关
 * @Date: Created in 10:49 2018/9/11
 * @Modified By:
 */
@Controller
@Slf4j
@RequestMapping("/order")
public class WechatOrderController {
    @Resource
    private IOrderWebService iOrderWebService;

    @Resource
    private JavaMailSenderImpl javaMailSender;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Resource
    private RedisPoolCache redisObj;

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * 订单文案跳转
     */
    @RequestMapping("/jump-story1")
    String orderStory1() {
        // iOrderWebService.insertOrderLog(OrderLogType.STORY_TYPE);
        return "order/story";
    }
    /**
     * 订单文案跳转
     */
    @RequestMapping("/pleasantly")
    String pleasantlyOrder() {
        // iOrderWebService.insertOrderLog(OrderLogType.STORY_TYPE);
        return "order/story";
    }
    /**
     * 订单文案跳转
     */
    @RequestMapping("/jump-story2")
    String orderStory() {
        // iOrderWebService.insertOrderLog(OrderLogType.STORY_TYPE);
        return "order/story1";
    }
    /**
     * 小说文案跳转
     */
    @RequestMapping(value="/jump-novel",produces = "text/html;charset=utf-8")
    @ResponseBody
    String jumpNovel(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("novel1837"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("order/newest-1837", ctx);
        redisObj.saveDataToRedis("novel1837",showhtml);
        return showhtml;
    }
    /**
     * 订单跳转
     */
    @RequestMapping(value="/jump-order",produces = "text/html;charset=utf-8")
    @ResponseBody
    String order(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (!HttpRequestDeviceUtils.isMobileDevice(request)) return "";
        iOrderWebService.insertOrderLog(OrderLogType.ORDER_TYPE);
        String showhtml = String.valueOf(redisObj.getRedisValueByKey("ORDERVAL99"));
        if(!StringUtils.isEmpty(showhtml) && !"null".equals(showhtml)){
            return  showhtml;
        }
        //手动渲染
        SpringWebContext ctx = new SpringWebContext(request,response,
                request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
        showhtml = thymeleafViewResolver.getTemplateEngine().process("order/order", ctx);
        redisObj.saveDataToRedis("ORDERVAL99",showhtml);

        return showhtml;
    }
    /**
     * 订单跳转
     */
    @RequestMapping("/jywa")
    String jinyou() {
        iOrderWebService.insertOrderLog(OrderLogType.JINYOU_TYPE);
        return "order/jinyou/wenan";
    }
//     @RequestMapping("confirm-order")
//     @ResponseBody
//     public RestResponseBo orderConfirm(OrderModelVo orderModelVo) {
//         iOrderWebService.insert(orderModelVo);
//         try {
//             //发送email
//             sendEmailWithOrder(orderModelVo);
//         } catch (Exception e) {
//             log.error("---------email发送错误");
//             e.printStackTrace();
//         }
//         log.info("----订单数据...{}", JSONObject.toJSONString(orderModelVo));
//         return RestResponseBo.ok();
//     }
//
//
//     private void sendEmailWithOrder(OrderModelVo orderModelVo) {
//         StringBuilder orderBuf = new StringBuilder();
// //        orderBuf.append("------------新的订单------------- \n");
//         orderBuf.append("姓名："+orderModelVo.getName()+"\n");
//         orderBuf.append("电话："+orderModelVo.getPhone()+"\n");
//         orderBuf.append("城市："+orderModelVo.getProvince()+orderModelVo
//                 .getCity()+orderModelVo.getCounty()+"\n");
//         orderBuf.append("地址："+orderModelVo.getAddress()+"\n");
//         orderBuf.append("商品类型："+orderModelVo.getTitle()+"\n");
//         orderBuf.append("尺寸大小："+orderModelVo.getSize()+"\n");
//         if(!StringUtils.isEmpty(orderModelVo.getMessage()))
//             orderBuf.append("顾客留言："+orderModelVo.getMessage());
//         MimeMessage msg = javaMailSender.createMimeMessage();
//         MimeMessageHelper helper = null;
//         try {
//             helper = new MimeMessageHelper(msg, true,"utf-8");
//             helper.setFrom("goodboy_bray@163.com");
//             helper.setCc("goodboy_bray@163.com");
//             helper.setTo(new String[]{"78901623@qq.com","937085200@qq.com","619105979@qq.com","527297994@qq.com","1194633142@qq.com","1318134732@qq.com"});
//             helper.setText(orderBuf.toString());
//             helper.setSubject("来了新的订单");
//         } catch (MessagingException e) {
//             e.printStackTrace();
//         }
//
// //        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
// //        simpleMailMessage.setSubject("有新的订单");
// //        simpleMailMessage.setText(orderBuf.toString());
// //        simpleMailMessage.setTo("1318134732@qq.com");
// //        simpleMailMessage.setFrom("goodboy_bray@163.com");
// //        Properties prop = new Properties();
// //        //解决ip与域名不匹配的问题
// //        prop.put("mail.smtp.localhost","mail.163.com");
// //        prop.put("mail.smtp.auth", "false");
// ////        prop.put("mail.smtp.port", "25");
// //        javaMailSender.setJavaMailProperties(prop);
//         javaMailSender.send(msg);
//     }
}