package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test.sekolah.dto.*;
import org.test.sekolah.entity.*;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.repository.MataPelajaranRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MataPelajaranServiceImpl implements MataPelajaranService {

    @Autowired
    private MataPelajaranRepository mataPelajaranRepository;

    @Autowired
    private KelasService kelasService;

    @Autowired
    private GuruService guruService;

    @Autowired
    private PelajaranService pelajaranService;

    @Override
    public ResponseMataPelajaranDTO getById(Long id) {
        Optional<MataPelajaran> byId = mataPelajaranRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Mata Pelajaran not found");
        return new ResponseMataPelajaranDTO(byId.get());
    }

    @Override
    public ResponseCustomPaging<ResponseMataPelajaranDTO> findAllPaging(String search, int page, int size) {
        if (Objects.isNull(search)) search = "";
        Pageable pageRequest = PageRequest.of(page - 1, size);
        Page<MataPelajaran> findAll = mataPelajaranRepository.findAllPaging(search, pageRequest);

        Page<ResponseMataPelajaranDTO> map = findAll.map(ResponseMataPelajaranDTO::new);
        return new ResponseCustomPaging<>(map);
    }

    @Override
    public MataPelajaran findById(Long id) {
        Optional<MataPelajaran> byId = mataPelajaranRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Mata Pelajaran not found");
        return byId.get();
    }

    @Override
    public ResponseMataPelajaranDTO create(RequestCreateMataPelajaranDTO dto) {
        Kelas kelas = kelasService.getById(dto.getKelasId());
        Pelajaran pelajaran = pelajaranService.getById(dto.getPelajaranId());
        Guru guru = guruService.findById(dto.getGuruId());

        MataPelajaran mata = new MataPelajaran();
        mata.setId(0L);
        mata.setKelas(kelas);
        mata.setPelajaran(pelajaran);
        mata.setGuru(guru);
        mata = mataPelajaranRepository.save(mata);

        return new ResponseMataPelajaranDTO(mata);
    }

    @Override
    public ResponseMataPelajaranDTO update(Long id, RequestCreateMataPelajaranDTO dto) {
        Kelas kelas = kelasService.getById(dto.getKelasId());
        Pelajaran pelajaran = pelajaranService.getById(dto.getPelajaranId());
        Guru guru = guruService.findById(dto.getGuruId());

        MataPelajaran mata = findById(id);
        mata.setKelas(kelas);
        mata.setPelajaran(pelajaran);
        mata.setGuru(guru);
        mata = mataPelajaranRepository.save(mata);

        return new ResponseMataPelajaranDTO(mata);
    }

    @Override
    public void deleteMataPelajaran(Long id) {
        MataPelajaran findById = findById(id);
        mataPelajaranRepository.delete(findById);
    }

    @Override
    public List<MataPelajaran> mataPelajaranByKelasId(long kelasId) {
        return mataPelajaranRepository.getListMataPelajaranByKelasId(kelasId);
    }

}
