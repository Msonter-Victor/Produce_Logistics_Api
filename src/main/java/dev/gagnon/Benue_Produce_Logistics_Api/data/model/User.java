package dev.gagnon.Benue_Produce_Logistics_Api.data.model;

import dev.gagnon.Benue_Produce_Logistics_Api.data.constants.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.GenerationType.AUTO;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "__users")
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    @ElementCollection(fetch=EAGER)
    @Enumerated(STRING)
    private Set<Role>roles;
}
