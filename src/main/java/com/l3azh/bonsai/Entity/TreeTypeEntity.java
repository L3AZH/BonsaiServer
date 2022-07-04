package com.l3azh.bonsai.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tree_type")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TreeTypeEntity extends BaseEntity {

    @Id
    @Type(type = "uuid-char")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "UUID_TreeType", columnDefinition = "NVARCHAR(50)")
    private UUID uuidTreeType;

    @Column(name = "Name", columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(name = "Description", columnDefinition = "NVARCHAR(100)")
    private String description;

    @OneToMany(mappedBy = "theTreeType")
    List<TreeEntity> listTree;

}
