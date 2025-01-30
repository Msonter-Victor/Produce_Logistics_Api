package dev.gagnon.Benue_Produce_Logistics_Api.security.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.security.data.models.BlackListedToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BlackTokenRepository extends JpaRepository<BlackListedToken, UUID> {
    Optional<BlackListedToken> findByToken(String token);
}
