package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.sekolah.entity.Kelas;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.repository.KelasRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KelasServiceImpl implements KelasService {

    @Autowired
    private KelasRepository kelasRepository;

    @Override
    public List<Kelas> getListKelas() {
        return kelasRepository.findAll();
    }

    @Override
    public Kelas getById(long id) {
        Optional<Kelas> byId = kelasRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Kelas not found");
        return byId.get();
    }

    @Override
    public void createKelas(long tingkatKelas, String kelasName) {
        Kelas kelas = new Kelas();
        kelas.setId(0L);
        kelas.setTingkat(tingkatKelas);
        kelas.setName(kelasName);
        kelasRepository.save(kelas);
    }

    @Override
    public void updateKelas(long id, long tingkatKelas, String kelasName) {
        Kelas kelas = getById(id);
        kelas.setTingkat(tingkatKelas);
        kelas.setName(kelasName);
        kelasRepository.save(kelas);
    }

    @Override
    public void deleteKelas(long id) {
        Kelas kelas = getById(id);
        kelasRepository.delete(kelas);
    }

}
