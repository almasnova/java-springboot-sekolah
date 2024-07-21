package org.test.sekolah.service;

import org.test.sekolah.dto.RequestCreateRaporSiswaDTO;
import org.test.sekolah.dto.RequestRaporSiswaDTO;
import org.test.sekolah.dto.ResponseRaporSiswaDTO;
import org.test.sekolah.entity.Kelas;

import java.util.List;

public interface RaporService {
    ResponseRaporSiswaDTO getBySiswaId(Long siswaId, RequestRaporSiswaDTO dto);

    void createRapor(Long siswaId, RequestCreateRaporSiswaDTO dto);
}
