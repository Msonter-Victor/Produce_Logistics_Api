package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface FarmerRepository extends JpaRepository<Farmer, UUID> {
    @Query("select f from Farmer f where f.bioData.email=:email")
    Optional<Farmer> findByEmail(String email);
}
