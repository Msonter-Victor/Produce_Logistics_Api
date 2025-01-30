package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.LogisticsProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RiderResponse {
    private UUID id;
    private String firstName;
    private String phone;
    private String imageUrl;

    public RiderResponse(LogisticsProvider logisticsProvider) {
        this.id = logisticsProvider.getId();
        firstName = logisticsProvider.getBioData().getFirstName();
        phone = logisticsProvider.getBioData().getPhone();
        imageUrl = logisticsProvider.getBioData().getImageUrl();
    }
}
