package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import dev.gagnon.Benue_Produce_Logistics_Api.data.constants.PAYMENT_MODE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRideRequest {
    private UUID orderId;
    private UUID riderId;
    private UUID buyerId;
    private String pickupAddress;
    private String destinationAddress;
    private PAYMENT_MODE paymentMode;
}
