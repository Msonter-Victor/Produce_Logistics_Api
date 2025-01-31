package dev.gagnon.Benue_Produce_Logistics_Api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@Getter
public class MailConfig {
    @Value("${SPRING_MAIL_HOST}")
    private String host;

    @Value("${SPRING_MAIL_PORT}")
    private int port;

    @Value("${SPRING_MAIL_USERNAME}")
    private String username;

    @Value("${SPRING_MAIL_PASSWORD}")
    private String password;

    @Value("${SPRING_MAIL_PROTOCOL}")
    private String protocol;
    @Value("${SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH}")
    private String smtpAuth;

    @Value("${SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE}")
    private String startTlsEnable;

    @Value("${SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_REQUIRED}")
    private boolean starttlsRequired;


    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);

        // SMTP properties
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", smtpAuth);
        props.put("mail.smtp.starttls.enable", startTlsEnable);
        props.put("mail.smtp.starttls.required", starttlsRequired);
        return mailSender;
    }

}
