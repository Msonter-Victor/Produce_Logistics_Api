package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class AddAccountDetailsResponse {
    @JsonFormat(pattern = "dd-MMMM-yyyy 'at' hh:mm a")
    private LocalDateTime responseTime;
    @JsonProperty("landlord_id")
    private UUID id;
    private String accountNumber;
    private String accountName;
    private String bankName;
}

