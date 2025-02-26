package dev.gagnon.Benue_Produce_Logistics_Api.security.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.GenerationType.IDENTITY;
import static java.time.LocalDateTime.now;
import static lombok.AccessLevel.NONE;

@Entity
@Table(name = "blacklisted_tokens")
@Getter
@Setter
public class BlacklistedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, length = 1000)
    private String token;
    private Instant expiresAt;
    @Setter(NONE)
    private LocalDateTime blacklistedAt;

    @PrePersist
    private void setBlacklistedAt() {
        blacklistedAt = now();
    }
}