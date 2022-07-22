package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBillDetailRepository extends JpaRepository<BillDetailEntity, BillDetailEntity.BillDetailKey> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM bill_detail bd WHERE bd.FK_Tree_UUID_Tree = :uuidTree"
    )
    List<BillDetailEntity> getListBillDetailByTree(String uuidTree);
}
