package org.test.sekolah.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RequestUserToken {

    @NotNull(message = "Field `nomerInduk` is required")
    @NotEmpty(message = "Field `nomerInduk` must not be empty")
    private String nip;

    @NotNull(message = "Field `Password` is required")
    @NotEmpty(message = "Field `Password` must not be empty")
    private String password;

}
