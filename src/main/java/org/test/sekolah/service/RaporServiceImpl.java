package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.sekolah.dto.*;
import org.test.sekolah.entity.HistoryKelasSiswa;
import org.test.sekolah.entity.MataPelajaran;
import org.test.sekolah.entity.NilaiRapor;
import org.test.sekolah.entity.Siswa;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.repository.HistoryKelasSiswaRepository;
import org.test.sekolah.repository.NilaiRaporRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RaporServiceImpl implements RaporService {

    @Autowired
    private HistoryKelasSiswaRepository historyKelasSiswaRepository;

    @Autowired
    private NilaiRaporRepository nilaiRaporRepository;

    @Autowired
    private SiswaService siswaService;

    @Autowired
    private KelasService kelasService;

    @Autowired
    private MataPelajaranService mataPelajaranService;

    @Override
    public ResponseRaporSiswaDTO getBySiswaId(Long siswaId, RequestRaporSiswaDTO dto) {
        Siswa siswa = siswaService.findById(siswaId);
        long kelasId = siswaService.getKelasIdByHistoryByIdAndTingkatKelas(siswaId, dto.getKelas());
        List<NilaiRaporInterface> rapor = siswaService.getNilaiRaporByKelasAndSemester(siswaId, kelasId, dto.getSemester());

        List<ResponseRaporPelajaranDTO> listNilai = new ArrayList<>();
        if (!rapor.isEmpty()) {
            for (NilaiRaporInterface s : rapor) {
                MataPelajaran mataPelajaran = mataPelajaranService.findById(s.getMataPelajaranId());
                ResponseRaporPelajaranDTO nilai = new ResponseRaporPelajaranDTO();
                nilai.setId(s.getId());
                nilai.setSemester(s.getSemester());
                nilai.setMataPelajaranId(s.getMataPelajaranId());
                nilai.setMataPelajaran(mataPelajaran.getPelajaran().getName());
                nilai.setGuruId(mataPelajaran.getGuru().getId());
                nilai.setGuru(mataPelajaran.getGuru().getName());
                nilai.setNilai(s.getNilai());
                listNilai.add(nilai);
            }
        }

//        if (rapor.isEmpty()) {
//            RequestCreateRaporSiswaDTO dtoRapor = new RequestCreateRaporSiswaDTO();
//
//            List<RequestCreateNilaiRaporDTO> nilai = new ArrayList<>();
//            dtoRapor.setKelas(dto.getKelas());
//            dtoRapor.setSemester(dto.getSemester());
//            List<MataPelajaran> mataPelajarans = mataPelajaranService.mataPelajaranByKelasId(kelasId);
//            for (MataPelajaran m : mataPelajarans)
//                nilai.add(new RequestCreateNilaiRaporDTO(m.getId(), 0.0));
//            dtoRapor.setNilai(nilai);
//
//            ResponseRaporSiswaDTO raporNew = createRapor(siswaId, dtoRapor);
//
//            return new ResponseRaporSiswaDTO(siswa, raporNew.getListNilai());
//        }
        return new ResponseRaporSiswaDTO(siswa, listNilai);
    }

    @Override
    public void createRapor(Long siswaId, RequestCreateRaporSiswaDTO dto) {
        Siswa siswa = siswaService.findById(siswaId);
        Optional<HistoryKelasSiswa> history = historyKelasSiswaRepository.getByTingkatKelas(siswaId, dto.getKelas());

        if (!history.isPresent()) throw new DataNotFoundException("Kelas not found");
        HistoryKelasSiswa his = history.get();


        List<NilaiRapor> nilaiData = nilaiRaporRepository.getListByHistoryId(his.getId());
        if (!nilaiData.isEmpty()) throw new DataNotValidException("Nilai sudah ada");

        for (RequestCreateNilaiRaporDTO req : dto.getNilai()) {
            MataPelajaran mataPelajaran = mataPelajaranService.findById(req.getMataPelajaranId());
            NilaiRapor nilai = new NilaiRapor();
            nilai.setId(0);
            nilai.setSemester(dto.getSemester());
            nilai.setMataPelajaran(mataPelajaran);
            nilai.setNilai(req.getNilai());
            nilai.setHistory(his);
            nilaiRaporRepository.save(nilai);
        }
    }

}
