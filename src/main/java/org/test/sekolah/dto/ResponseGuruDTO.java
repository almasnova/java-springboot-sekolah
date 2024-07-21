package org.test.sekolah.dto;

import lombok.Data;
import org.test.sekolah.entity.Guru;

@Data
public class ResponseGuruDTO {
    private Long id;
    private String nip;
    private String name;
    private String phone;
    private boolean master;
    private Long dateLastLogin;
    private String alamat;

    public ResponseGuruDTO(Guru guru) {
        this.id = guru.getId();
        this.nip = guru.getNip();
        this.name = guru.getName();
        this.phone = guru.getPhone();
        this.alamat = guru.getAlamat();
        this.master = guru.isMaster();
        this.dateLastLogin = guru.getDateLastLogin();
    }
}
