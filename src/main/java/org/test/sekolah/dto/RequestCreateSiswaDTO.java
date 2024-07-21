package org.test.sekolah.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class RequestCreateSiswaDTO {

    @NotNull(message = "Field 'nis' is required")
    protected String nis;

    @NotNull(message = "Field 'name' is required")
    protected String name;

    @NotNull(message = "Field 'phone' is required")
    @Pattern(regexp = "8[0-9]{5,17}", message = "Field 'phone' invalid or limit characters is 18")
    protected String phone;

    @NotNull(message = "Field 'alamat' is required")
    protected String alamat;

    @NotNull(message = "Field 'kelasId' is required")
    protected long kelasId;

}

