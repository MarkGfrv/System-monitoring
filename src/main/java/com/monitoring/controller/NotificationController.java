package com.monitoring.controller;

import com.monitoring.model.Notification;
import com.monitoring.service.NotificationService;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public Notification addNotification(@RequestBody Notification notification) {
        notification.setTimestamp(LocalDateTime.now());
        notificationService.checkAndSendAlert(notification);
        return notification;
    }
}