package org.example.backendproject_restapi.utils;

import org.example.backendproject_restapi.models.ErrorResponse;
import org.example.backendproject_restapi.models.Response;
import org.example.backendproject_restapi.models.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseEntityUtil {
    public static <T> ResponseEntity<Object> createResponseEntity(Map<String, T> body, HttpStatus httpStatus, String message) {
        if (httpStatus == null) {
            if (body == null) {
                httpStatus = HttpStatus.NO_CONTENT;
            } else {
                httpStatus = HttpStatus.OK;
            }
        }
        Response response = body == null && !httpStatus.is2xxSuccessful()
                ? new ErrorResponse(message)
                : new SuccessResponse<>(body, message);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static <T> ResponseEntity<Object> createResponseEntity(Map<String, T> body, String message) {
        return createResponseEntity(body, null, message);
    }

    public static <T> ResponseEntity<Object> createResponseEntity(Map<String, T> body) {
        return createResponseEntity(body, null, null);
    }
}
