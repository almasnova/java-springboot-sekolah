package org.test.sekolah.dto;

import lombok.Data;
import org.test.sekolah.entity.Guru;
import org.test.sekolah.entity.Kelas;
import org.test.sekolah.entity.MataPelajaran;
import org.test.sekolah.entity.Pelajaran;

import javax.persistence.*;

@Data
public class ResponseMataPelajaranDTO {
    private Long id;
    private Long kelasId;
    private int tingkatKelas;
    private String kelasName;
    private Long guruId;
    private String guruName;
    private Long pelajaranId;
    private String pelajaranName;

    public ResponseMataPelajaranDTO(MataPelajaran mataPelajaran) {
        this.id = mataPelajaran.getId();
        this.kelasId = mataPelajaran.getKelas().getId();
        this.tingkatKelas = mataPelajaran.getKelas().getTingkat();
        this.kelasName = mataPelajaran.getKelas().getName();
        this.guruId = mataPelajaran.getGuru().getId();
        this.guruName = mataPelajaran.getGuru().getName();
        this.pelajaranId = mataPelajaran.getPelajaran().getId();
        this.pelajaranName = mataPelajaran.getPelajaran().getName();
    }
}
