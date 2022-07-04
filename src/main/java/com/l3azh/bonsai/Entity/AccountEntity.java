package com.l3azh.bonsai.Entity;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountEntity extends BaseEntity {

    @Id
    @Column(name = "Email", columnDefinition = "NVARCHAR(50)")
    private String email;

    @Column(name = "Password", columnDefinition = "NVARCHAR(1000)")
    private String password;

    @Column(name = "FirstName", columnDefinition = "NVARCHAR(50)")
    private String firstName;

    @Column(name = "LastName", columnDefinition = "NVARCHAR(50)")
    private String lastName;

    @Column(name = "PhoneNumber", columnDefinition = "VARCHAR(10)")
    private String phoneNumber;

    @Column(name = "Role", columnDefinition = "VARCHAR(45)")
    private String role;

    @Column(name = "Avatar", columnDefinition = "BLOB")
    private byte[] avatar;

    @OneToMany(mappedBy = "accountOfBill")
    List<BillEntity> listBill;

}
