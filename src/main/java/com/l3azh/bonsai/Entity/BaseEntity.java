package com.l3azh.bonsai.Entity;

import com.l3azh.bonsai.Util.AppUtils;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity {
    @Column(name = "CreateDate", columnDefinition = "DATETIME")
    private Date createDate;
    @Column(name = "UpdateDate", columnDefinition = "DATETIME")
    private Date updateDate;

    @PrePersist
    public void defaultValue(){
        if(Objects.isNull(createDate)){
            createDate = new Date();
        }
        if(Objects.isNull(updateDate)){
            updateDate = new Date();
        }
    }
}
