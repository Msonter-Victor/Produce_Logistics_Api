package dev.gagnon.Benue_Produce_Logistics_Api.service;


import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.SendMailRequest;

import java.io.IOException;

public interface EmailSenderService {
    String sendEmail(SendMailRequest sendMailRequest) throws IOException;
}
