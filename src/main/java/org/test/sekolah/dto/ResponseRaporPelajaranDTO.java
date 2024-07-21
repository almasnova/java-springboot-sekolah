package org.test.sekolah.dto;

import lombok.Data;
import org.test.sekolah.entity.NilaiRapor;

@Data
public class ResponseRaporPelajaranDTO {
    private Long id;
    private int semester;
    private Long mataPelajaranId;
    private String mataPelajaran;
    private Long guruId;
    private String guru;
    private double nilai;

    public ResponseRaporPelajaranDTO(NilaiRapor nilaiRapor) {
        this.id = nilaiRapor.getId();
        this.semester = nilaiRapor.getSemester();
        this.mataPelajaranId = nilaiRapor.getMataPelajaran().getId();
        this.mataPelajaran = nilaiRapor.getMataPelajaran().getPelajaran().getName();
        this.guruId = nilaiRapor.getMataPelajaran().getGuru().getId();
        this.guru = nilaiRapor.getMataPelajaran().getGuru().getName();
        this.nilai = nilaiRapor.getNilai();
    }
}
