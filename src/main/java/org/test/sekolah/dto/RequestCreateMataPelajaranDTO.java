package org.test.sekolah.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestCreateMataPelajaranDTO {

    @NotNull(message = "Field 'guruId' is required")
    protected long guruId;

    @NotNull(message = "Field 'pelajaranId' is required")
    protected long pelajaranId;

    @NotNull(message = "Field 'kelasId' is required")
    protected long kelasId;

}

