package org.test.sekolah.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mata_pelajaran")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MataPelajaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "kelas_id", nullable = false)
    private Kelas kelas;

    @OneToOne
    @JoinColumn(name = "pelajaran_id", nullable = false)
    private Pelajaran pelajaran;

    @OneToOne
    @JoinColumn(name = "guru_id", nullable = false)
    private Guru guru;

}
