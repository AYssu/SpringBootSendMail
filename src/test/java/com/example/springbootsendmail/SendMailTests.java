package com.example.springbootsendmail;

import com.example.springbootsendmail.service.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendMailTests {

    @Autowired
    private SendMailService sendMailService;
    @Test
    void sendHtml() {
        sendMailService.sendHtmlMail("2997036064@qq.com", "网页测试", "你好呀网页");
    }

    @Test
    void sendText() {
        sendMailService.sendTextMail("2997036064@qq.com", "文本测试", "你好呀文本");
    }

    @Test
    void sendCode()
    {
        Integer random = (int)((Math.random()*9+1)*100000);
        sendMailService.sendCodeMail("2997036064@qq.com",random);
    }
}
