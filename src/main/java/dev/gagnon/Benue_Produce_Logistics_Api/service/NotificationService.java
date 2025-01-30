package dev.gagnon.Benue_Produce_Logistics_Api.service;

import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.NotificationRequest;

public interface NotificationService {
    String sendNotification(NotificationRequest request);
}
