package com.xykj.omapp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * @author ocean
 * @Title: OceanEmial
 * @ProjectName omProject
 * @Description: TODO
 * @date 2018/10/4下午1:26
 */
@Component
public class OceanEmial {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromUser; //读取配置文件中的参数

    public void sendSimpleEmail(String toUser,String subject,String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromUser);
        message.setTo(toUser); //自己给自己发送邮件
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
        System.out.println("发送成功");
    }

    @Async
    public void sendHtmlEmial(String toUser,String subject,String html){
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromUser);
            helper.setTo(toUser);
            helper.setSubject(subject);
            helper.setText(html, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        javaMailSender.send(message);
    }

}
