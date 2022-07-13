package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.BillDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillDetailRepository extends JpaRepository<BillDetailEntity, BillDetailEntity.BillDetailKey> {
}
