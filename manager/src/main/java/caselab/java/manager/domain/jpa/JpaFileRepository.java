package caselab.java.manager.domain.jpa;

import caselab.java.manager.domain.entity.FileEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaFileRepository extends JpaRepository<FileEntity, Long> {

    @Query(value = "SELECT * FROM files ORDER BY creation_date", nativeQuery = true)
    List<FileEntity> findAllWithSort();
}
