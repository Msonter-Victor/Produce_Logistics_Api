package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailRequest {
    private String sendTo;
    private String subject;
    private String content;
}
