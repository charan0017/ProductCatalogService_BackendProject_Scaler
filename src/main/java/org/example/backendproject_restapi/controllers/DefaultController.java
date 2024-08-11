package org.example.backendproject_restapi.controllers;

import org.example.backendproject_restapi.models.ErrorResponse;
import org.example.backendproject_restapi.utils.ResponseEntityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
public class DefaultController {
    @Value("${spring.application.name}")
    private String appName;
    @Value("${server.port}")
    private Integer port;
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

    @RequestMapping(value = "{*path}")
    public ResponseEntity<ErrorResponse> redirect(@PathVariable(value = "path") String requestPath) {
        String message = "Invalid path requested: \"http://localhost:" + port.toString() + requestPath + "\"";
        return new ResponseEntity<>(new ErrorResponse(message, this), HttpStatus.BAD_REQUEST);
    }
}
