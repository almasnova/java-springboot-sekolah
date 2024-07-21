package org.test.sekolah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "nilai_rapor")
@Data
@Entity
public class NilaiRapor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "semester")
    private int semester;

    @OneToOne
    @JoinColumn(name = "mata_pelajaran_id", nullable = false)
    private MataPelajaran mataPelajaran;

    @Column(name = "nilai")
    private double nilai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_kelas_siswa_id", nullable = false)
    private HistoryKelasSiswa history;
}
