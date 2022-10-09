package com.yoho.blamarket.common;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

public class MailUtils {

    private JavaMailSender mailSender;
    private MimeMessage message;
    private MimeMessageHelper messageHelper;


    public MailUtils(JavaMailSender mailSender) throws MessagingException {

        this.mailSender = mailSender;
        message = this.mailSender.createMimeMessage();
        messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    }

    public void setTo(String email) throws MessagingException{
        messageHelper.setTo(email);
    }
    public void setSubject(String subject) throws MessagingException{
        messageHelper.setSubject(subject);
    }

    public void setText(String htmlContent) throws MessagingException{
        messageHelper.setText(htmlContent);
    }

    public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException{
        messageHelper.setFrom(email, name);
    }

    public void addInline(String contentId, DataSource dataSource) throws MessagingException {
        messageHelper.addInline(contentId, dataSource);
    }

    public void send() {
        mailSender.send(message);
    }

}
