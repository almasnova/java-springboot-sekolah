package org.test.sekolah.service;

import org.test.sekolah.entity.Kelas;

import java.util.List;

public interface KelasService {
    List<Kelas> getListKelas();

    Kelas getById(long id);

    void createKelas(long tingkatKelas, String kelasName);

    void updateKelas(long id, long tingkatKelas, String kelasName);

    void deleteKelas(long id);
}
