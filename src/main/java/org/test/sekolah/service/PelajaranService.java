package org.test.sekolah.service;

import org.test.sekolah.entity.Pelajaran;

import java.util.List;

public interface PelajaranService {
    List<Pelajaran> getListPelajaran();

    Pelajaran getById(long id);

    void createPelajaran(String pelajaranName);

    void updatePelajaran(long id, String pelajaranName);

    void deletePelajaran(long id);
}
