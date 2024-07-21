package org.test.sekolah.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import springfox.documentation.swagger.web.UiConfiguration;

@Data
public class BasicResponse<T> {
    private String message;
    private T content;
    private boolean isSuccess;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UiConfiguration uiConfig;

    public void setFailedResponse(String message) {
        this.message = message;
        this.content = null;
        this.isSuccess = false;
    }

    public void setSuccessResponse(T t) {
        this.message = "";
        this.content = t;
        this.isSuccess = true;
    }

    public BasicResponse() {}

    public BasicResponse(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }
}
