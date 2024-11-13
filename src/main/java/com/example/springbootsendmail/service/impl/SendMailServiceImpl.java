package com.example.springbootsendmail.service.impl;

import com.example.springbootsendmail.service.SendMailService;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String email;
    @Override
    public void sendTextMail(String to, String title, String message) {
        try {
            // 使用sampleMail 发送邮箱也许 但是指定发送名称会被默认为邮箱名称
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(email,"阿夜","UTF-8"));
            // 收件人邮箱
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(title);

            // false表示文本格式
            mimeMessageHelper.setText(message, false);
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }

    @Override
    public void sendHtmlMail(String to, String title, String message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(email,"阿夜","UTF-8"));
            // 收件人邮箱
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(title);

            Context context = new Context();
            // 收件人昵称
            context.setVariable("text",message);
            String contents = templateEngine.process("textTemplate",context);

            // true表明为HTML格式邮件
            mimeMessageHelper.setText(contents, true);
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }

    @Override
    public void sendCodeMail(String mail, Integer random) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(new InternetAddress(email,"阿夜","UTF-8"));
            // 收件人邮箱
            mimeMessageHelper.setTo(mail);
            mimeMessageHelper.setSubject("验证码");

            Context context = new Context();
            // 收件人昵称
            context.setVariable("code",random);
            String contents = templateEngine.process("codeTemplate",context);

            // true表明为HTML格式邮件
            mimeMessageHelper.setText(contents, true);
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }

}
