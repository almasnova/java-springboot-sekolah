package org.test.sekolah.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "kelas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Kelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tingkat")
    private long tingkat;

    @Column(name = "name")
    private String name;

}
