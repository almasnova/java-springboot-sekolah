package org.test.sekolah.dto;

import lombok.Data;
import org.test.sekolah.entity.MataPelajaran;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RequestCreateNilaiRaporDTO {

    @NotNull(message = "Field 'mataPelajaranId' is required")
    protected long mataPelajaranId;

    @NotNull(message = "Field 'nilai' is required")
    protected double nilai;

    public RequestCreateNilaiRaporDTO(Long mataPelajaranId, double nilai) {
        this.mataPelajaranId = mataPelajaranId;
        this.nilai = nilai;
    }
}

