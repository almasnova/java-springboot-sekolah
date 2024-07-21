package org.test.sekolah.dto;

import lombok.Data;
import org.test.sekolah.entity.HistoryKelasSiswa;
import org.test.sekolah.entity.Siswa;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseSiswaDTO {
    private Long id;
    private String nis;
    private String name;
    private String phone;
    private String alamat;
    private String kelas;

    private List<String> historyKelas;

    public ResponseSiswaDTO(Siswa siswa) {
        this.id = siswa.getId();
        this.nis = siswa.getNis();
        this.name = siswa.getName();
        this.phone = siswa.getPhone();
        this.alamat = siswa.getAlamat();
        this.kelas = siswa.getKelas().getName();

        this.historyKelas = new ArrayList<>();
    }

    public ResponseSiswaDTO(Siswa siswa, List<HistoryKelasSiswa> historyKelas) {
        this.id = siswa.getId();
        this.nis = siswa.getNis();
        this.name = siswa.getName();
        this.phone = siswa.getPhone();
        this.alamat = siswa.getAlamat();
        this.kelas = siswa.getKelas().getName();

        this.historyKelas = new ArrayList<>();
        if (!historyKelas.isEmpty()) {
            for (HistoryKelasSiswa h : historyKelas)
                this.historyKelas.add(h.getKelas().getName());
        }
    }
}
