package org.example.backendproject_restapi.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import lombok.Data;
import org.example.backendproject_restapi.enums.StatusEnum;

import java.util.UUID;

@Data
public abstract class BaseDto {
    @Valid

    @Id
    private UUID id;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status = StatusEnum.ACTIVE;
}
