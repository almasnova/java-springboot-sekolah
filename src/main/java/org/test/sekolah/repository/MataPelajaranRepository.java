
package org.test.sekolah.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.MataPelajaran;

import java.util.List;
import java.util.Optional;

@Repository
public interface MataPelajaranRepository extends JpaRepository<MataPelajaran, Long> {

    @Query("SELECT g FROM MataPelajaran g WHERE " +
            "(LOWER(CONCAT(g.guru.name)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.pelajaran.name)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.kelas.name)) like LOWER(CONCAT('%', :search,'%')))")
    Page<MataPelajaran> findAllPaging(@Param("search") String search,
                                      Pageable pageable);

    @Query(nativeQuery = true, value = "select * from mata_pelajaran where id = :id limit 1")
    Optional<MataPelajaran> findById(@Param("id") long id);

    @Query(nativeQuery = true, value = "select * from mata_pelajaran where kelas_id = :kelasId")
    List<MataPelajaran> getListMataPelajaranByKelasId(@Param("kelasId") long kelasId);
}
