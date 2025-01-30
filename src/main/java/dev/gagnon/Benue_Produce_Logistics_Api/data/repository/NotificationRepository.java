package dev.gagnon.Benue_Produce_Logistics_Api.data.repository;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
