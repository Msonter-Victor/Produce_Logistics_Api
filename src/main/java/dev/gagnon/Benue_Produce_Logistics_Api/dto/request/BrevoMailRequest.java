package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BrevoMailRequest {
    private Sender sender;
    @JsonProperty("to")
    private List<Recipient> recipients;
    private String subject;
    @JsonProperty("htmlContent")
    private String content;
}
