package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FindRiderRequest {
    private double latitude;
    private double longitude;
}
