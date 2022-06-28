package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.TreeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ITreeTypeRepository extends JpaRepository<TreeTypeEntity, UUID> {
}
