package org.test.sekolah.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ResponseOk {
    private String message;
//    private Long id;

    public ResponseOk(List<String> x) {
        for (String a : x) {
            this.message = this.message + " " + a;
        }
    }

    public ResponseOk(String x) {
        this.message = x;
    }
}
