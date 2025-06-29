package com.monitoring.controller;

import com.monitoring.model.SystemSettings;
import com.monitoring.repository.SystemSettingsRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {
    private final SystemSettingsRepository settingsRepo;

    public SettingsController(SystemSettingsRepository settingsRepo) {
        this.settingsRepo = settingsRepo;
    }

    @GetMapping
    public SystemSettings getSettings() {
        return settingsRepo.findById(1L).orElse(new SystemSettings());
    }

    @PostMapping
    public SystemSettings updateSettings(@RequestBody SystemSettings settings) {
        settings.setId(1L);
        return settingsRepo.save(settings);
    }
}