package com.xykj.omapp;

import com.xykj.omapp.business.impl.UserBusinessImpl;
import com.xykj.omapp.utils.OceanEmial;
import com.xykj.ombase.utils.OceanOperationUtil;
import com.xykj.omservice.user.po.TUserPo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class OmAppApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    OceanEmial oceanEmial;
    @Autowired
    UserBusinessImpl userBusiness;

    @Test
    public void sendSimpleMail() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("<h1>大标题-h1</h1>")
                .append("<p style='color:#F00'>红色字</p>")
                .append("<p style='text-align:right'>右对齐</p>")
                .append("https://mail.qq.com/cgi-bin/frame_html?sid=PGL-GZD9pW3YNiJ5&r=9e8e87c2ea3c1dcec006f7763421b6c7");
        oceanEmial.sendHtmlEmial("348686686@qq.com","这是html主题",sb.toString());
        System.out.println("发送成功");
    }

    @Test
    public void testSimple(){
        oceanEmial.sendSimpleEmail("348686686@qq.com","这是主题","这是内容");
    }

    @Test
    public void testRegister(){
        TUserPo tUserPo = new TUserPo();
        tUserPo.setUserName("大家好，我是陆超");
        tUserPo.setPassword(OceanOperationUtil.md5("123qwe"));
        tUserPo.setEmail("348686686@qq.com");
        userBusiness.register(tUserPo,"http://localhost:8088");
    }

    @Autowired
    TemplateEngine templateEngine;

    @Test
    public void testTemplate(){
        Context context = new Context();
        context.setVariable("username","李长昕");
        context.setVariable("adminEmail","348686686@qq.com");
        String content = templateEngine.process("emailTemplate",context);
        oceanEmial.sendHtmlEmial("348686686@qq.com","模板邮件",content);
    }

    @Test
    public void getIp(){
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress.getAddress().toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
