package dev.gagnon.Benue_Produce_Logistics_Api.service;



import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.SendMailRequest;

import java.io.IOException;

public interface MailService {
    String sendConfirmationMail(SendMailRequest request) throws IOException;
}
