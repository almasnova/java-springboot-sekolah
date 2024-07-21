
package org.test.sekolah.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.test.sekolah.dto.NilaiRaporInterface;
import org.test.sekolah.entity.HistoryKelasSiswa;
import org.test.sekolah.entity.NilaiRapor;
import org.test.sekolah.entity.Siswa;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiswaRepository extends JpaRepository<Siswa, Long> {

    @Query("SELECT g FROM Siswa g WHERE g.deleted=false AND " +
            " (LOWER(CONCAT(g.name)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.nis)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.alamat)) like LOWER(CONCAT('%', :search,'%')) " +
            "OR LOWER(CONCAT(g.phone)) like LOWER(CONCAT('%', :search,'%')))")
    Page<Siswa> findAllPaging(@Param("search") String search,
                              Pageable pageable);

    @Query(nativeQuery = true, value = "select * from siswa where id = :id and deleted = 0 limit 1")
    Optional<Siswa> findById(@Param("id") long id);

    @Query(nativeQuery = true, value = "select history_kelas_siswa.kelas_id from history_kelas_siswa inner join kelas" +
            " on history_kelas_siswa.kelas_id=kelas.id where" +
            " history_kelas_siswa.siswa_id = :id and kelas.tingkat = :tingkat limit 1")
    Long getKelasIdByHistoryByIdAndTingkatKelas(@Param("id") long id,
                                                @Param("tingkat") long tingkatKelas);

//    @Query(nativeQuery = true, value = "select nilai_rapor.* from siswa inner join history_kelas_siswa on " +
//            "siswa.id=history_kelas_siswa.siswa_id inner join nilai_rapor on " +
//            "history_kelas_siswa.id = nilai_rapor.history_kelas_siswa_id " +
//            "where siswa.id=:id and history_kelas_siswa.kelas_id=:kelasId and nilai_rapor.semester=:semester")
//    @Query(value = "select n from NilaiRapor n " +
//            "inner join n.history h" +
//            "inner join h.siswa s" +
//            "where s.id=:id and h.kelasId=:kelasId and n.semester=:semester")
//    List<NilaiRapor> getNilaiRaporByKelasIdAndSemester(@Param("id") long id,
//                                                       @Param("kelasId") long kelasId,
//                                                       @Param("semester") long semester);

    @Query(nativeQuery = true, value = "select nilai_rapor.id as id, nilai_rapor.semester as semester, " +
            "nilai_rapor.mata_pelajaran_id as mataPelajaranId, nilai_rapor.nilai as nilai, " +
            "nilai_rapor.history_kelas_siswa_id as historyKelasSiswaId " +
            " from siswa inner join history_kelas_siswa on " +
            "siswa.id=history_kelas_siswa.siswa_id inner join nilai_rapor on " +
            "history_kelas_siswa.id = nilai_rapor.history_kelas_siswa_id " +
            "where siswa.id=:id and history_kelas_siswa.kelas_id=:kelasId and nilai_rapor.semester=:semester")
    List<NilaiRaporInterface> getNilaiRaporByKelasIdAndSemester(@Param("id") long id,
                                                                @Param("kelasId") long kelasId,
                                                                @Param("semester") long semester);
}
