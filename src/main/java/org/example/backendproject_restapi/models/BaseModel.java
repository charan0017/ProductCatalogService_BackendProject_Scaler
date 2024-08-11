package org.example.backendproject_restapi.models;

import lombok.Getter;
import lombok.Setter;
import org.example.backendproject_restapi.enums.StatusEnum;

import java.util.Date;

@Getter
@Setter
public abstract class BaseModel {
    private Long id;

    private Date created_at;

    private Date updated_at;

    private StatusEnum status;
}
