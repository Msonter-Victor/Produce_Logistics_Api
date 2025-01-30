package dev.gagnon.Benue_Produce_Logistics_Api.data.model;

import dev.gagnon.Benue_Produce_Logistics_Api.data.constants.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "_users")
public class BioData {
    @Id
    @GeneratedValue
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String imageUrl;
    @ElementCollection(fetch=EAGER)
    @Enumerated(STRING)
    private Set<Role> roles;
}

