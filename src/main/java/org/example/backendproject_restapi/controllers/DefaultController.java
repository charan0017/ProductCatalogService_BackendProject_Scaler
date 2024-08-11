package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.utils.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
public class DefaultController {
    @Value("${spring.application.name}")
    private String appName;
    private final Date date = new Date();

    @GetMapping("/ping")
    public ResponseEntity<Object> ping() {
        return ResponseEntityUtil.createResponseEntity(
                new HashMap<>() {{
                    put("appName", appName);
                    put("date", date);
                }}
        );
    }
}
