package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import dev.gagnon.Benue_Produce_Logistics_Api.config.MailConfig;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.SendMailRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.service.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.io.IOException;

import static dev.gagnon.Benue_Produce_Logistics_Api.utils.ServiceUtils.SENDER_EMAIL;

@Service
public class BdicMailService implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    public BdicMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public String sendEmail(SendMailRequest sendMailRequest) throws IOException {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true for multipart

            // Set the from email dynamically or default to the one configured in MailConfig

            helper.setFrom(SENDER_EMAIL);
            helper.setTo(sendMailRequest.getRecipientEmail());
            helper.setSubject("Registration Confirmation");
            helper.setText(sendMailRequest.getContent());

            // Send the email
            javaMailSender.send(message);

            return "Mail sent successfully";
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
