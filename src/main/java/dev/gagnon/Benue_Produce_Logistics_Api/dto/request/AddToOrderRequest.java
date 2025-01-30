package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddToOrderRequest {
    private UUID productId;
    private UUID orderId;
    private int quantity;
}
