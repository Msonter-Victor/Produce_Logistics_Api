package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
