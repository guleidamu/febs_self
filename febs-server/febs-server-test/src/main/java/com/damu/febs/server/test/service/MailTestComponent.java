package com.damu.febs.server.test.service;

import com.damu.febs.server.test.data.dto.MailDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MailTestComponent {

    @Resource
    private JavaMailSender javaMailSender;

    public void sendEmailMesssage(MailDto mailDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom("364632363@qq.com");
        helper.setSubject(mailDto.getSubject());
        List<String> addressList = mailDto.getAddress();
        String[] addressArray = new String[addressList.size()];
        for (int i = 0; i < addressArray.length; i++) {
            addressArray[i] = addressList.get(i);
        }
        helper.setTo(addressArray);
        helper.setText(mailDto.getContent(), true);
        javaMailSender.send(message);
    }

    public void sendEmailMessageBySelf(MailDto mailDto) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("192.168.90.212");
//      mailSender.setPort(465);
        mailSender.setUsername("xmn.its@xmnmail.kerryeas.com");
        mailSender.setPassword("K1rry1as");
        //加认证机制
//        Properties javaMailProperties = new Properties();
//        javaMailProperties.put("mail.smtp.auth", true);
//        javaMailProperties.put("mail.smtp.starttls.enable", true);
//        javaMailProperties.put("mail.smtp.timeout", 5000);
//        mailSender.setJavaMailProperties(javaMailProperties);
        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("");
        helper.setSubject(mailDto.getSubject());
        List<String> mailAddressList = mailDto.getAddress();
        String[] addressArray = new String[mailAddressList.size()];
        for (int i = 0; i < addressArray.length; i++) {
            addressArray[i] = mailAddressList.get(i);
        }
        helper.setTo(addressArray);
        helper.setText(mailDto.getContent(), true);
        Map<String, FileSystemResource> fileMap = mailDto.getFileMap();
        Set<String> strings = fileMap.keySet();
        for (String fileName : strings) {
            helper.addAttachment(fileName, fileMap.get(fileName));
        }
        mailSender.send(message);
    }

    public static void main(String[] args) {
        String toMailAddress = "meili,ai,chang,ge";
        List<String> mailAddressList = Arrays.asList(toMailAddress.split(","));
        System.out.println(mailAddressList);
        String str = StringUtils.join(mailAddressList, ",");
        System.out.println("通过list转化成string，" + str);
    }

}
