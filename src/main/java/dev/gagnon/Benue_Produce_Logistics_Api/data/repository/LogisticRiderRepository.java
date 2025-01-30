package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.LogisticsProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface LogisticRiderRepository extends JpaRepository<LogisticsProvider, UUID> {
    @Query("select r from LogisticsProvider r where r.bioData.email=:email")
    Optional<LogisticsProvider> findByEmail(String email);
}
