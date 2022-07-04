package com.l3azh.bonsai.Entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "bill_detail")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BillDetailEntity{

    @Embeddable
    @Data
    private static class BillDetailKey implements Serializable {

        @Type(type = "uuid-char")
        @Column(name = "FK_Tree_UUID_Tree", columnDefinition = "NVARCHAR(50)")
        private UUID fkUUIDTree;

        @Type(type = "uuid-char")
        @Column(name = "FK_Bill_UUID_Bill", columnDefinition = "NVARCHAR(50)")
        private UUID fkUUIDBill;
    }

    @EmbeddedId
    private BillDetailKey billDetailKey;

    @Column(name = "Quantity", columnDefinition = "INT")
    private Integer quantity;

    @Column(name = "PriceSold", columnDefinition = "DOUBLE")
    private Double priceSold;

    @Column(name = "CreateDate", columnDefinition = "DATETIME")
    private Date createDate;

    @ManyToOne
    @MapsId(value = "fkUUIDTree")
    @JoinColumn(name = "FK_Tree_UUID_Tree")
    TreeEntity treeOfBillDetail;

    @ManyToOne
    @MapsId(value = "fkUUIDBill")
    @JoinColumn(name = "FK_Bill_UUID_Bill")
    BillEntity billOfBillDetail;
}


