package dev.gagnon.Benue_Produce_Logistics_Api.dto.request;

import dev.gagnon.Benue_Produce_Logistics_Api.data.constants.Role;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
