package org.test.sekolah.service;

import org.test.sekolah.dto.*;
import org.test.sekolah.entity.HistoryKelasSiswa;
import org.test.sekolah.entity.NilaiRapor;
import org.test.sekolah.entity.Siswa;

import java.util.List;

public interface SiswaService {

    ResponseSiswaDTO getById(Long id);

    ResponseCustomPaging<ResponseSiswaDTO> findAllPaging(String search, int page, int size);

    Siswa findById(Long id);

    ResponseSiswaDTO createSiswa(RequestCreateSiswaDTO dto);

    ResponseSiswaDTO updateSiswa(Long id, RequestCreateSiswaDTO dto);

    void deleteSiswa(Long id);

    List<NilaiRapor> getNilaiRaporByKelasAndSemester(long id, long kelasId, int semester);

//    ResponseSiswaDTO createHistoryKelas(Long id, RequestCreateHistoryKelasSiswaDTO dto);

    Long getKelasIdByHistoryByIdAndTingkatKelas(Long siswaId, int kelas);
}
