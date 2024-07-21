
package org.test.sekolah.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.Guru;

import java.util.Optional;

@Repository
public interface GuruRepository extends JpaRepository<Guru, Long> {

    @Query("SELECT g FROM Guru g WHERE g.deleted=false AND " +
            " (LOWER(CONCAT(g.name)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.nip)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.alamat)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.phone)) like LOWER(CONCAT('%', :search,'%')))")
    Page<Guru> findAllPaging(@Param("search") String search,
                             Pageable pageable);

    @Query(nativeQuery = true, value = "select * from guru where id = :id and deleted = 0 limit 1")
    Optional<Guru> findById(@Param("id") long id);

    @Query(nativeQuery = true, value = "select * from guru where nip = :nip and deleted = 0 limit 1")
    Optional<Guru> findByNip(@Param("nip") String nip);

    @Query(nativeQuery = true, value = "SELECT * FROM guru WHERE phone=:phone and nip=:nip and deleted = 0 limit 1")
    Optional<Guru> findByPhoneAndNipJwt(@Param("phone") String phone, @Param("nip") String nip);
}
