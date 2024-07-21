
package org.test.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.Pelajaran;

import java.util.List;
import java.util.Optional;

@Repository
public interface PelajaranRepository extends JpaRepository<Pelajaran, Long> {

    @Query("SELECT g FROM Pelajaran g")
    List<Pelajaran> findAll();

    @Query(nativeQuery = true, value = "select * from pelajaran where id = :id limit 1")
    Optional<Pelajaran> findById(@Param("id") long id);

}
