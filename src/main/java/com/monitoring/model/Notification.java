package com.monitoring.model;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timestamp;
    private String componentId;
    private String description;

    @Enumerated(EnumType.STRING)
    private SeverityLevel severity;

    public enum SeverityLevel {
    INFO, WARNING, CRITICAL
    }
}