package service;
import javax.mail.*;

import java.util.Properties;

import javax.mail.internet.*;

import com.sun.mail.util.MailSSLSocketFactory;


public class MailUtil implements Runnable {
    private String email;
    private String code;

    public MailUtil(String email, String code) {
        this.email = email;
        this.code = code;
    }

    public void run() {
        String from = "";// 发件人电子邮箱
        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证

        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);


            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("", ""); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(from));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h2>欢迎注册Mask,激活请点击以下链接</h2><h3><a href='http://localhost:8080/mask/Active?code="
                    + code + "'>点击此处</a></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

