package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BioDataRepository extends JpaRepository<BioData, UUID> {
    boolean existsByEmail(String email);
    Optional<BioData> findByEmail(String email);
}
