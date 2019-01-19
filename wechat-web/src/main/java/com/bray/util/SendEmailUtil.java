package com.bray.util;

import com.alibaba.fastjson.JSONObject;
import com.bray.model.Vo.OrderModelVo;
import com.bray.model.WySafedomain;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Author:wuzhiyuan
 * @Description: 发送邮件相关
 * @Date: Created in 17:10 2018/9/18
 * @Modified By:
 */
public class SendEmailUtil {
    /**
     * 发送订单邮件
     * @param orderModelVo
     */
    public static void sendEmailWithOrder(OrderModelVo orderModelVo) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("有新的订单");
        simpleMailMessage.setText(JSONObject.toJSONString(orderModelVo));
        simpleMailMessage.setTo("1318134732@qq.com");
        simpleMailMessage.setFrom("goodboy_bray@163.com");
        javaMailSender.send(simpleMailMessage);
    }

    public static void sendEmail(JavaMailSenderImpl javaMailSender,OrderModelVo orderModelVo,String mailsql) {
        StringBuilder orderBuf = new StringBuilder();
//        orderBuf.append("------------新的订单------------- \n");
        orderBuf.append("详情:\n " + orderModelVo.getName() + "\n"+orderModelVo.getPhone()+ "\n" +orderModelVo.getProvince() + orderModelVo
                .getCity() + orderModelVo.getCounty() +orderModelVo.getAddress()+"\n"+orderModelVo.getTitle()+"\n"+orderModelVo.getSize()+"\n"+orderModelVo.getMessage());
        orderBuf.append("\n");
        orderBuf.append("\n"+orderModelVo.getMessage());
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        String[] mails = null;
        if(StringUtils.isEmpty(mailsql))
            mails = new String[]{"1318134732@qq.com"};
        else
            mails = mailsql.split(",");
        try {
            helper = new MimeMessageHelper(msg, true, "utf-8");
            helper.setFrom("1318134732@qq.com");
            helper.setCc("1318134732@qq.com");
            // helper.setTo(new String[]{ "78901623@qq.com",  "527297994@qq.com", "1194633142@qq.com", "1318134732@qq.com"});
            helper.setTo(mails);
            helper.setText(orderBuf.toString().replace("￥",""));
            helper.setSubject("新年前的冲刺！【"+orderModelVo.getName()+"】");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(msg);
    }

    private void sendEmailOther(JavaMailSenderImpl javaMailSender,OrderModelVo orderModelVo) {
        StringBuilder orderBuf = new StringBuilder();
//        orderBuf.append("------------新的订单------------- \n");
        orderBuf.append("姓名：" + orderModelVo.getName() + "\n");
        orderBuf.append("电话：" + orderModelVo.getPhone() + "\n");
        orderBuf.append("城市：" + orderModelVo.getProvince() + orderModelVo
                .getCity() + orderModelVo.getCounty() + "\n");
        orderBuf.append("地址：" + orderModelVo.getAddress() + "\n");
        orderBuf.append("商品类型：" + orderModelVo.getTitle() + "\n");
        orderBuf.append("尺寸大小：" + orderModelVo.getSize() + "\n");
        if (!StringUtils.isEmpty(orderModelVo.getMessage()))
            orderBuf.append("顾客留言：" + orderModelVo.getMessage());
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(msg, true, "utf-8");
            helper.setFrom("goodboy_ooooo@163.com");
            helper.setCc("goodboy_ooooo@163.com");
            helper.setTo(new String[]{"78901623@qq.com", "937085200@qq.com", "619105979@qq.com", "527297994@qq.com", "1194633142@qq.com", "1318134732@qq.com"});
            helper.setText(orderBuf.toString().replace("￥",""));
            helper.setSubject("新年前的冲刺!!【" + orderModelVo.getName() + "】");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(msg);
    }
}
