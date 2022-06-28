package com.l3azh.bonsai.Entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.Date;

@Data
@SuperBuilder
public class BaseEntity {
    @Column(name = "CreateDate", columnDefinition = "DATETIME")
    private Date createDate;
    @Column(name = "UpdateDate", columnDefinition = "DATETIME")
    private Date updateDate;
}
