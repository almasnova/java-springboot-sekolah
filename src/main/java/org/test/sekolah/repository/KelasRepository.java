
package org.test.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.Kelas;

import java.util.List;
import java.util.Optional;

@Repository
public interface KelasRepository extends JpaRepository<Kelas, Long> {

    @Query("SELECT g FROM Kelas g")
    List<Kelas> findAll();

    @Query(nativeQuery = true, value = "select * from kelas where id = :id limit 1")
    Optional<Kelas> findById(@Param("id") long id);

}
