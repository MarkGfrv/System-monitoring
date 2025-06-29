package com.monitoring.repository;

import com.monitoring.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    long countBySeverity(Notification.SeverityLevel severity);
}