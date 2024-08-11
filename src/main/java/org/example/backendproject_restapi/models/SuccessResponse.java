package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SuccessResponse<T> extends Response {
    private Map<String, T> data;

    @Override
    public void setMessage(String message) {
        super.setMessage(message != null && !message.isEmpty() ? message : "Success");
    }

    public SuccessResponse(Map<String, T> data, String message) {
        this.setStatus(true);
        this.setMessage(message);
        this.setData(data);
    }

    public SuccessResponse(String message) {
        this(new HashMap<>(), message);
    }

    public void addData(String key, T data) {
        this.data.put(key, data);
    }

    public void removeData(String key) {
        this.data.remove(key);
    }
}
