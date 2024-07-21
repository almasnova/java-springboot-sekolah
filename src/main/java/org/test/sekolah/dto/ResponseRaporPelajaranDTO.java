package org.test.sekolah.dto;

import lombok.Data;
import org.test.sekolah.entity.NilaiRapor;

@Data
public class ResponseRaporPelajaranDTO {
    private long id;
    private long semester;
    private long mataPelajaranId;
    private String mataPelajaran;
    private long guruId;
    private String guru;
    private double nilai;

//    public ResponseRaporPelajaranDTO(NilaiRapor nilaiRapor) {
//        this.id = nilaiRapor.getId();
//        this.semester = nilaiRapor.getSemester();
//        this.mataPelajaranId = nilaiRapor.getMataPelajaran().getId();
//        this.mataPelajaran = nilaiRapor.getMataPelajaran().getPelajaran().getName();
//        this.guruId = nilaiRapor.getMataPelajaran().getGuru().getId();
//        this.guru = nilaiRapor.getMataPelajaran().getGuru().getName();
//        this.nilai = nilaiRapor.getNilai();
//    }
}
