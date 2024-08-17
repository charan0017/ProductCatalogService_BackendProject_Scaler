package org.example.backendproject_restapi.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.Data;
import org.example.backendproject_restapi.dtos.validator.groups.OnCreate;
import org.example.backendproject_restapi.enums.StatusEnum;
import org.example.backendproject_restapi.helpers.ValidEnum;

@Data
public abstract class BaseDto {
    @Valid

    @Id
    @Null(groups = {OnCreate.class}, message = "Id should not be provided")
    private String id;

    @Enumerated(EnumType.ORDINAL)
    @ValidEnum(enumClass = StatusEnum.class, message = "Status is invalid")
    private StatusEnum status = StatusEnum.ACTIVE;
}
