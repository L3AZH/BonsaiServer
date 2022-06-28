package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.TreeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITreeTypeRepository extends JpaRepository<TreeTypeEntity, UUID> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM tree_type tt WHERE tt.Name = :nameTreeType"
    )
    Optional<List<TreeTypeEntity>> getListTreeTypeByName(String nameTreeType);
}
