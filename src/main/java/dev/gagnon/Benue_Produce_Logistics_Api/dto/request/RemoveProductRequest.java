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
public class RemoveProductRequest {
    private UUID orderId;
    private UUID productId;
    private int quantity;
}
