
package org.test.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.entity.HistoryKelasSiswa;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryKelasSiswaRepository extends JpaRepository<HistoryKelasSiswa, Long> {

    @Query(nativeQuery = true, value = "select history_kelas_siswa.* from history_kelas_siswa inner join kelas" +
            " on history_kelas_siswa.kelas_id=kelas.id where" +
            " history_kelas_siswa.siswa_id = :siswaId and kelas.tingkat = :tingkat limit 1")
    Optional<HistoryKelasSiswa> getByTingkatKelas(@Param("siswaId") long siswaId,
                                                  @Param("tingkat") int tingkatKelas);

    @Query(nativeQuery = true, value = "select h from HistoryKelasSiswa h where h.siswaId = :siswaId")
    List<HistoryKelasSiswa> getListHistoryBySiswaId(@Param("siswaId") long siswaId);
}
