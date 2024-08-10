package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private long id;

    private Date created_at;

    private Date updated_at;

    private Status status;
}
