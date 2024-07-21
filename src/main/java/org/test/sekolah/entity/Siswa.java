package org.test.sekolah.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "siswa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Siswa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nis")
    private String nis;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "alamat")
    private String alamat;

    @OneToOne
    @JoinColumn(name = "kelas_id")
    private Kelas kelas;

    @Column(name = "date_modified")
    private Long dateModified;

    @Column(name = "date_created")
    private Long dateCreated;

    @Column(name = "deleted")
    private boolean deleted;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "siswa_id", nullable = false)
//    private List<HistoryKelasSiswa> historyKelas;

}
