package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddProductResponse {
    private UUID productId;
    private String message;
}
