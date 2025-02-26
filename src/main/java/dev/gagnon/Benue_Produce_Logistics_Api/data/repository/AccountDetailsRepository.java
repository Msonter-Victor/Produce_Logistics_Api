package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountDetailsRepository extends JpaRepository<AccountDetails, UUID> {
}
