package com.l3azh.bonsai.Entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tree")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TreeEntity extends BaseEntity{

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UUID_Tree", columnDefinition = "NVARCHAR(50)")
    private UUID uuidTree;

    @Column(name = "Name", columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(name = "Description", columnDefinition = "NVARCHAR(100)")
    private String description;

    @Column(name = "Price", columnDefinition = "DOUBLE")
    private Double price;

    @Column(name = "Picture", columnDefinition = "MEDIUMBLOB")
    private byte[] picture;

    @ManyToOne
    @JoinColumn(name = "FK_TreeType_UUID_TreeType")
    private TreeTypeEntity theTreeType;

    @OneToMany(mappedBy = "treeOfBillDetail")
    private List<BillDetailEntity> listBillDetail;
}
