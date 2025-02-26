package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.SendMailRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class BdicMailService implements EmailSenderService {

    private final JavaMailSender javaMailSender;
    @Value("${SPRING_MAIL_USERNAME}")
    private String username;

    public BdicMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(SendMailRequest mailRequest) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        try {

            mimeMessageHelper.setTo(mailRequest.getSendTo());
            mimeMessageHelper.setSubject(mailRequest.getSubject());
            mimeMessageHelper.setFrom(this.username);
            mimeMessageHelper.setText(mailRequest.getContent(), true);

            new Thread(() -> {

                System.out.println("Sending mail...");
                javaMailSender.send(mimeMessage);
                System.out.println("Mail sent successfully...");
            }).start();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
