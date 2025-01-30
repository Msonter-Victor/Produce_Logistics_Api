package dev.gagnon.Benue_Produce_Logistics_Api.dto.response;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private String message;
    private String email;
}
