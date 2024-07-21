package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.test.sekolah.dto.*;
import org.test.sekolah.entity.*;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.repository.HistoryKelasSiswaRepository;
import org.test.sekolah.repository.SiswaRepository;
import org.test.sekolah.util.Constants;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SiswaServiceImpl implements SiswaService {

    @Autowired
    private SiswaRepository siswaRepository;

    @Autowired
    private HistoryKelasSiswaRepository historyKelasSiswaRepository;

    @Autowired
    private KelasService kelasService;

    @Override
    public ResponseSiswaDTO getById(Long id) {
        Optional<Siswa> byId = siswaRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Siswa not found");
        return new ResponseSiswaDTO(byId.get(), historyKelasSiswaRepository.getListHistoryBySiswaId(byId.get().getId()));
    }

    @Override
    public ResponseCustomPaging<ResponseSiswaDTO> findAllPaging(String search, int page, int size) {
        if (Objects.isNull(search)) search = "";
        Pageable pageRequest = PageRequest.of(page - 1, size);
        Page<Siswa> findAll = siswaRepository.findAllPaging(search, pageRequest);

        Page<ResponseSiswaDTO> map = findAll.map(ResponseSiswaDTO::new);
        return new ResponseCustomPaging<>(map);
    }

    @Override
    public Siswa findById(Long id) {
        Optional<Siswa> byId = siswaRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Siswa not found");
        return byId.get();
    }

    @Override
    public ResponseSiswaDTO createSiswa(RequestCreateSiswaDTO dto) {
        Kelas kelas = kelasService.getById(dto.getKelasId());

        Siswa siswa = new Siswa();
        siswa.setId(0L);
        siswa.setNis(dto.getNis());
        siswa.setName(dto.getName());
        siswa.setPhone(dto.getPhone());
        siswa.setKelas(kelas);
        siswa.setAlamat(dto.getAlamat());
        siswa.setDateCreated(Constants.getTimestamp());
        siswa.setDeleted(false);
        siswa = siswaRepository.save(siswa);

        HistoryKelasSiswa his = new HistoryKelasSiswa();
        his.setId(0L);
        his.setSiswa(siswa);
        his.setKelas(kelas);
        historyKelasSiswaRepository.save(his);

        return new ResponseSiswaDTO(siswa);
    }

    @Override
    public ResponseSiswaDTO updateSiswa(Long id, RequestCreateSiswaDTO dto) {
        Kelas kelas = kelasService.getById(dto.getKelasId());

        Siswa findById = findById(id);
        findById.setNis(dto.getNis());
        findById.setName(dto.getName());
        findById.setPhone(dto.getPhone());
        findById.setAlamat(dto.getAlamat());
        findById.setKelas(kelas);
        findById.setDateModified(Constants.getTimestamp());
        Siswa siswa = siswaRepository.save(findById);

        return new ResponseSiswaDTO(siswa);
    }

    @Override
    public void deleteSiswa(Long id) {
        Siswa findById = findById(id);
        findById.setDeleted(true);
        siswaRepository.save(findById);
    }

    @Override
    public Long getKelasIdByHistoryByIdAndTingkatKelas(Long siswaId, int kelas) {
        Long kelasId = siswaRepository.getKelasIdByHistoryByIdAndTingkatKelas(siswaId, kelas);
        if (kelasId == null) throw new DataNotFoundException("Kelas not found");
        return kelasId;
    }

    @Override
    public List<NilaiRapor> getNilaiRaporByKelasAndSemester(long id, long kelasId, int semester) {
        return siswaRepository.getNilaiRaporByKelasIdAndSemester(id, kelasId, semester);
    }

//    @Override
//    public ResponseSiswaDTO createHistoryKelas(Long id, RequestCreateHistoryKelasSiswaDTO dto) {
//        Siswa siswa = findById(id);
//        List<Kelas> kelasSekarang = siswa.getHistoryKelas().stream().map(HistoryKelasSiswa::getKelas).collect(Collectors.toList());
//        List<Long> kelasSekarangIds = kelasSekarang.stream().map(Kelas::getId).collect(Collectors.toList());
//
//        for (Long k : dto.getKelasIds()) {
//            Kelas kelas = kelasService.getById(k);
////            if(kelasSekarangIds.contains(kelas.getId()))
//
//
//        }
//        return null;
//    }

}
