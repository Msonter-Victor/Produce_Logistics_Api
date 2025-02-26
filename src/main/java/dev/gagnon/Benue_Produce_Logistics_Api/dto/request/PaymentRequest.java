package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PaymentRequest {
    private UUID orderId;
    private double amount;
}
