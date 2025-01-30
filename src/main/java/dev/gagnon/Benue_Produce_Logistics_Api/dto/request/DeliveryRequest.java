package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryRequest {
    private UUID orderId;
}
