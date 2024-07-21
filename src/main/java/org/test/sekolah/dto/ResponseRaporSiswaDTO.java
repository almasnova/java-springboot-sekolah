package org.test.sekolah.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.sekolah.entity.NilaiRapor;
import org.test.sekolah.entity.Siswa;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ResponseRaporSiswaDTO {
    private Long id;
    private String nis;
    private String siswaName;
    private String phone;
    private String alamat;
    private String kelas;

    private List<ResponseRaporPelajaranDTO> listNilai;

    public ResponseRaporSiswaDTO(Siswa siswa, List<ResponseRaporPelajaranDTO> rapor) {
        this.id = siswa.getId();
        this.nis = siswa.getNis();
        this.siswaName = siswa.getName();
        this.phone = siswa.getPhone();
        this.alamat = siswa.getAlamat();
        this.kelas = siswa.getKelas().getName();

        this.listNilai = rapor;
//        if (!rapor.isEmpty()) {
//            for (NilaiRapor s : rapor)
//                this.listNilai.add(new ResponseRaporPelajaranDTO(s));
//        }
    }
}
