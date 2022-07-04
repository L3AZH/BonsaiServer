package com.l3azh.bonsai.Entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bill")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BillEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UUID_Bill", columnDefinition = "NVARCHAR(50)")
    private UUID uuidBill;

    @Column(name = "CreateDate", columnDefinition = "DATETIME")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "FK_Account_Email")
    private AccountEntity accountOfBill;

    @OneToMany(mappedBy = "billOfBillDetail")
    private List<BillDetailEntity> listBillDetail;
}
