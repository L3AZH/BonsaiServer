package com.l3azh.bonsai.Repository;

import com.l3azh.bonsai.Entity.TreeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ITreeRepository extends JpaRepository<TreeEntity, UUID> {

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM tree tr WHERE tr.Name = :nameTree"
    )
    Optional<List<TreeEntity>> getListTreeByName(@Param(value = "nameTree") String nameTree);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM tree tr WHERE tr.Name LIKE %:nameTree%"
    )
    List<TreeEntity> getListTreeContainNameSearch(@Param(value = "nameTree") String nameTree);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM tree tr WHERE tr.FK_TreeType_UUID_TreeType = :uuidTreeType"
    )
    Optional<List<TreeEntity>> getListTreeByTreeType(String uuidTreeType);
}
