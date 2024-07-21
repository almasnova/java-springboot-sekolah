package org.test.sekolah.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuruToken {
    private Long id;
    private String phone;
    private String nip;
}
