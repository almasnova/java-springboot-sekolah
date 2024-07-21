package org.test.sekolah.service;

import org.test.sekolah.dto.RequestCreateMataPelajaranDTO;
import org.test.sekolah.dto.ResponseCustomPaging;
import org.test.sekolah.dto.ResponseMataPelajaranDTO;
import org.test.sekolah.entity.MataPelajaran;

import java.util.List;

public interface MataPelajaranService {

    ResponseMataPelajaranDTO getById(Long id);

    MataPelajaran findById(Long id);

    ResponseCustomPaging<ResponseMataPelajaranDTO> findAllPaging(String search, int page, int size);

    ResponseMataPelajaranDTO create(RequestCreateMataPelajaranDTO dto);

    ResponseMataPelajaranDTO update(Long id, RequestCreateMataPelajaranDTO dto);

    void deleteMataPelajaran(Long id);

    List<MataPelajaran> mataPelajaranByKelasId(long kelasId);
}
