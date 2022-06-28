package com.l3azh.bonsai.Entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "bill_detail")
@Data
@Builder
public class BillDetailEntity {

    @Embeddable
    @Data
    private static class BillDetailKey implements Serializable {

        @Type(type = "uuid-char")
        @Column(name = "FK_Tree_UUID_Tree")
        private UUID fkUUIDTree;

        @Type(type = "uuid-char")
        @Column(name = "FK_Bill_UUID_Bill")
        private UUID fkUUIDBill;
    }

    @EmbeddedId
    private BillDetailKey billDetailKey;

    @Column(name = "Quantity", columnDefinition = "INT")
    private Integer quantity;

    @ManyToOne
    @MapsId(value = "fkUUIDTree")
    @JoinColumn(name = "FK_Tree_UUID_Tree")
    TreeEntity treeOfBillDetail;

    @ManyToOne
    @MapsId(value = "fkUUIDBill")
    @JoinColumn(name = "FK_Bill_UUID_Bill")
    BillEntity billOfBillDetail;
}


