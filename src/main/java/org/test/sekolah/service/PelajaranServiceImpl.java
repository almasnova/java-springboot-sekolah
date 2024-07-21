package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.sekolah.entity.Kelas;
import org.test.sekolah.entity.Pelajaran;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.repository.KelasRepository;
import org.test.sekolah.repository.PelajaranRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PelajaranServiceImpl implements PelajaranService {

    @Autowired
    private PelajaranRepository pelajaranRepository;

    @Override
    public List<Pelajaran> getListPelajaran() {
        return pelajaranRepository.findAll();
    }

    @Override
    public Pelajaran getById(long id) {
        Optional<Pelajaran> byId = pelajaranRepository.findById(id);
        if (!byId.isPresent()) throw new DataNotFoundException("Pelajaran not found");
        return byId.get();
    }

    @Override
    public void createPelajaran(String pelajaranName) {
        Pelajaran pelajaran = new Pelajaran();
        pelajaran.setId(0L);
        pelajaran.setName(pelajaranName);
        pelajaranRepository.save(pelajaran);
    }

    @Override
    public void updatePelajaran(long id, String pelajaranName) {
        Pelajaran pelajaran = getById(id);
        pelajaran.setName(pelajaranName);
        pelajaranRepository.save(pelajaran);
    }

    @Override
    public void deletePelajaran(long id) {
        Pelajaran pelajaran = getById(id);
        pelajaranRepository.delete(pelajaran);
    }

}
