package org.test.sekolah.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.test.sekolah.entity.MataPelajaran;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateNilaiRaporDTO {

    @NotNull(message = "Field 'mataPelajaranId' is required")
    private long mataPelajaranId;

    @NotNull(message = "Field 'nilai' is required")
    private double nilai;

    public RequestCreateNilaiRaporDTO(Long mataPelajaranId, double nilai) {
        this.mataPelajaranId = mataPelajaranId;
        this.nilai = nilai;
    }
}

