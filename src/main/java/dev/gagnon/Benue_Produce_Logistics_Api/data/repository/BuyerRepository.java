package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface BuyerRepository extends JpaRepository<Buyer, UUID> {
    @Query("select b from Buyer b where b.bioData.email=:email")
    Optional<Buyer> findByEmail(String email);
}
