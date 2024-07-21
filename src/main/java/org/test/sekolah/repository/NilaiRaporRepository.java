
package org.test.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.NilaiRapor;

import java.util.List;

@Repository
public interface NilaiRaporRepository extends JpaRepository<NilaiRapor, Long> {

    @Query(nativeQuery = true, value = "select * from nilai_rapor where history_kelas_siswa_id = :id")
    List<NilaiRapor> getListByHistoryId(@Param("id") long id);

}
