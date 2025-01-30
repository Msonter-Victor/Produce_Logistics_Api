package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BdicApiResponse <T>{
    private boolean  isSuccessful;
    private T  data;
}
