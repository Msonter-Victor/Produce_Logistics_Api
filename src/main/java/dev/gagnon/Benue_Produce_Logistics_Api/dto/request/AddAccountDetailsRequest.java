package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddAccountDetailsRequest {
    private String accountNumber;
    private String accountName;
    private String bankName;
}