package dev.gagnon.Benue_Produce_Logistics_Api.security.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class BlackListedToken {
    @Id
    @GeneratedValue
    private UUID id;
    private String token;

    public BlackListedToken(String token) {
        this.token = token;
    }
}