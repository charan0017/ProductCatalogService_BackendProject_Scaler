package org.example.backendproject_restapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.backendproject_restapi.enums.StatusEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status = StatusEnum.ACTIVE;

    @Override
    public BaseModel clone() {
        try {
            return (BaseModel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
