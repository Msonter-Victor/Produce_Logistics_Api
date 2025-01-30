package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;


import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Farmer;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FarmerResponse {
    private UUID id;
    private String firstName;
    private String imageUrl;

    public FarmerResponse(Farmer farmer) {
        this.id = farmer.getId();
        this.firstName = farmer.getBioData().getFirstName();
        this.imageUrl = farmer.getBioData().getImageUrl();
    }
}
