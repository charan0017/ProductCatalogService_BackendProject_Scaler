package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Ping {
    private String appName;

    private final Date date = new Date();

    public Ping(String appName) {
        this.appName = appName;
    }
}
