package com.catering.service.common;

import com.catering.service.helper.CurrentUser;
import com.catering.service.helper.IdGeneratorHelper;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * @version 1.0.0
 * @Project catering-service-base-sceleton
 * @Author Afrail Hossain
 * @Since 1 January 2023
 */


@MappedSuperclass
@Data
@Accessors(chain = true)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String uuid;

    @NotNull
    private String createdBy;

    @NotNull
    private LocalDate createdDate;

    private String modifyBy;

    @Column(name = "IS_ACTIVE", columnDefinition = "boolean default true")
    private boolean active = true;

    private LocalDate modifiedDate;
    @PrePersist
    public void prePersist(){
        this.setUuid(IdGeneratorHelper.generateUUid());
        this.setCreatedBy(CurrentUser.getUsername());
        this.setModifiedDate(LocalDate.now());
        this.setCreatedDate(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate(){
        this.setModifyBy(CurrentUser.getUsername());
        this.setModifiedDate(LocalDate.now());
    }


}
