package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private Boolean status;
    private String message;
}
