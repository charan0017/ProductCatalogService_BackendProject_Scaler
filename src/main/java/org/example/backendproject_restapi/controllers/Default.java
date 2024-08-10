package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.models.Ping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class Default {
    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/ping")
    public Ping ping() {
        return new Ping(appName);
    }
}
