package dev.gagnon.Benue_Produce_Logistics_Api.service.impl;

import dev.gagnon.Benue_Produce_Logistics_Api.data.model.BioData;
import dev.gagnon.Benue_Produce_Logistics_Api.data.model.Notification;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.BioDataRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.data.repository.NotificationRepository;
import dev.gagnon.Benue_Produce_Logistics_Api.dto.request.NotificationRequest;
import dev.gagnon.Benue_Produce_Logistics_Api.exception.UserNotFoundException;
import dev.gagnon.Benue_Produce_Logistics_Api.service.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class BdicNotificationService implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final BioDataRepository bioDataRepository;

    public BdicNotificationService(NotificationRepository notificationRepository, BioDataRepository bioDataRepository) {
        this.notificationRepository = notificationRepository;
        this.bioDataRepository = bioDataRepository;
    }

    @Override
    public String sendNotification(NotificationRequest request) {
        BioData bioData = bioDataRepository.findById(request.getRecipientId())
                .orElseThrow(()->new UserNotFoundException("User Not Found"));
        Notification notification = new Notification();
        notification.setRecipient(bioData);
        notification.setMessage(request.getMessage());
        notificationRepository.save(notification);
        return "notification sent";
    }



}
