package org.test.sekolah.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResponseMain extends BasicResponse {

    private Object content;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    //roles !

    private Object info;

    public void setSuccess(Object body) {
        this.setSuccess(true);
        this.setMessage("");
        this.content = body;
    }

    public void setFail(String message) {
        this.setSuccess(false);
        this.setMessage(message);
        this.content = null;
    }

    // Khusus untuk delete setting, supaya error 400 dan tampilin semua unit/tenant office yang terikat
    public void setFailSetting(String message, Object body) {
        this.setSuccess(false);
        this.setMessage(message);
        this.content = body;
    }

}
