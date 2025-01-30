package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class OrderResponse {
    private UUID orderId;

}
