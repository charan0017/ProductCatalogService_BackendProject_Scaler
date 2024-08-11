package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends Response {
    @Override
    public void setMessage(String message) {
        super.setMessage(message != null && !message.isEmpty() ? message : "Error");
    }

    public ErrorResponse() {
        this.setStatus(false);
        this.setMessage("Error");
    }

    public ErrorResponse(String message) {
        this();
        this.setMessage(message);
    }
}
