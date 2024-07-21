package org.test.sekolah.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "history_kelas_siswa")
public class HistoryKelasSiswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "siswa_id", nullable = false)
    private Siswa siswa;

    @OneToOne
    @JoinColumn(name = "kelas_id", nullable = false)
    private Kelas kelas;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "history")
    private List<NilaiRapor> nilaiRapors;

}
