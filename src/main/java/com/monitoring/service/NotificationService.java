package com.monitoring.service;

import com.monitoring.model.Notification;
import com.monitoring.model.SystemSettings;
import com.monitoring.repository.NotificationRepository;
import com.monitoring.repository.SystemSettingsRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepo;
    private final SystemSettingsRepository settingsRepo;
    private final RabbitTemplate rabbitTemplate;

    public NotificationService(NotificationRepository notificationRepo, 
                            SystemSettingsRepository settingsRepo,
                            RabbitTemplate rabbitTemplate) {
        this.notificationRepo = notificationRepo;
        this.settingsRepo = settingsRepo;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void checkAndSendAlert(Notification notification) {
        notificationRepo.save(notification);
        
        SystemSettings settings = settingsRepo.findById(1L).orElseGet(() -> {
            SystemSettings newSettings = new SystemSettings();
            newSettings.setId(1L);
            return settingsRepo.save(newSettings);
        });

        long count = notificationRepo.countBySeverity(notification.getSeverity());
        
        if ((notification.getSeverity() == Notification.SeverityLevel.WARNING && count >= settings.getWarningThreshold()) ||
            (notification.getSeverity() == Notification.SeverityLevel.CRITICAL && count >= settings.getCriticalThreshold())) {
            
            String message = "Alert: " + count + " " + notification.getSeverity() + 
                          " events detected. Notify: " + settings.getRecipientEmails();
            rabbitTemplate.convertAndSend("email.queue", message);
        }
    }
}