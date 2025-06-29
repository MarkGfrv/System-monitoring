package com.monitoring.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "system_settings")
public class SystemSettings {
    @Id
    private Long id;

    private int warningThreshold = 10;
    private int criticalThreshold = 5;
    private String recipientEmails = "email@example.com";
}