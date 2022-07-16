package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IBillRepository extends JpaRepository<BillEntity, UUID> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM bill bi WHERE bi.FK_Account_Email = :email"
    )
    List<BillEntity> findByEmail(String email);
}
