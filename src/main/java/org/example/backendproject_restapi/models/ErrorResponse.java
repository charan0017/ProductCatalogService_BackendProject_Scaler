package org.example.backendproject_restapi.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
@Setter
public class ErrorResponse extends Response {
    @Getter(AccessLevel.PRIVATE) @Setter(AccessLevel.PRIVATE)
    private Logger logger;

    @Override
    public void setMessage(String message) {
        super.setMessage(message != null && !message.isEmpty() ? message : "Error");
    }

    public ErrorResponse(String message) {
        this.setStatus(false);
        this.setMessage(message);
    }

    public ErrorResponse() {
        this(null);
    }

    public ErrorResponse(String message, Object ClassInstance) {
        this("unable to process request");
        this.setLogger(
                LoggerFactory.getLogger(
                        (ClassInstance == null ? this : ClassInstance).getClass()
                )
        );
        this.getLogger().error(message);
    }
}
