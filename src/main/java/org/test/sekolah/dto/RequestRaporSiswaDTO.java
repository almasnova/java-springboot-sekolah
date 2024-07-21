package org.test.sekolah.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RequestRaporSiswaDTO {

    @NotNull(message = "Field 'kelas' is required")
    protected long kelas;

    @NotNull(message = "Field 'semester' is required")
    protected long semester;

}

