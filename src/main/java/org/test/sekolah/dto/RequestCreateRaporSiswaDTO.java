package org.test.sekolah.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class RequestCreateRaporSiswaDTO {

    @NotNull(message = "Field 'kelas' is required")
    protected int kelas;

    @NotNull(message = "Field 'semester' is required")
    protected int semester;

    @NotNull(message = "Field 'nilai' is required")
    @Size(min = 1, message = "Field 'nilai' must contains at least 1 element(s)")
    private List<RequestCreateNilaiRaporDTO> nilai;

}

