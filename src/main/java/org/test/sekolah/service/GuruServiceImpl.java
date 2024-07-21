package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.test.sekolah.bean.BCryptPasswordEncoding;
import org.test.sekolah.dto.RequestCreateGuruDTO;
import org.test.sekolah.dto.ResponseCustomPaging;
import org.test.sekolah.dto.ResponseGuruDTO;
import org.test.sekolah.entity.Guru;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.repository.GuruRepository;
import org.test.sekolah.util.Constants;

import java.util.*;

@Service
public class GuruServiceImpl implements GuruService {

    @Autowired
    private GuruRepository guruRepository;

    @Autowired
    private BCryptPasswordEncoding bCryptPasswordEncoding;

    @Override
    public Guru findByNip(String nip) {
        Optional<Guru> byNip = guruRepository.findByNip(nip);
        if (!byNip.isPresent()) throw new DataNotFoundException("Guru is deleted/doesn't exist");
        return byNip.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new RuntimeException("Method not implemented");
    }

    @Override
    public void setUserLogin(Guru guru) {
        guru.setDateLastLogin(Constants.getTimestamp());
        guruRepository.save(guru);
    }

    @Override
    public UserDetails findByPhoneAndNipJwt(String phone, String nip) {
        Optional<Guru> byPhoneAndNip = guruRepository.findByPhoneAndNipJwt(phone, nip);
        if (!byPhoneAndNip.isPresent()) throw new DataNotFoundException("Guru is deleted/doesn't exist");
        return byPhoneAndNip.get();
    }

    @Override
    public ResponseGuruDTO getGuruById(Long guruId) {
        Optional<Guru> byId = guruRepository.findById(guruId);
        if (!byId.isPresent()) throw new DataNotFoundException("Guru not found");
        return new ResponseGuruDTO(byId.get());
    }

    @Override
    public ResponseCustomPaging<ResponseGuruDTO> findAllPaging(Guru guru, String search, int page, int size) {
        if (!guru.isMaster()) throw new DataNotValidException("Account must be Master");

        if (Objects.isNull(search)) search = "";
        Pageable pageRequest = PageRequest.of(page - 1, size);
        Page<Guru> findAll = guruRepository.findAllPaging(search, pageRequest);

        Page<ResponseGuruDTO> map = findAll.map(ResponseGuruDTO::new);
        return new ResponseCustomPaging<>(map);
    }

    @Override
    public Guru findById(Long guruId) {
        Optional<Guru> byId = guruRepository.findById(guruId);
        if (!byId.isPresent()) throw new DataNotFoundException("Guru not found");
        return byId.get();
    }

    @Override
    public ResponseGuruDTO createGuru(RequestCreateGuruDTO dto) {
        Guru guru = new Guru();
        guru.setId(0L);
        guru.setNip(dto.getNip());
        guru.setName(dto.getName());
        guru.setPhone(dto.getPhone());
        guru.setMaster(dto.isMaster());
        guru.setAlamat(dto.getAlamat());
        guru.setDateCreated(Constants.getTimestamp());
        guru.setDeleted(false);
        guru.setPassword(bCryptPasswordEncoding.passwordEncoder().encode(dto.getPassword()));
        guru = guruRepository.save(guru);

        return new ResponseGuruDTO(guru);
    }

    @Override
    public ResponseGuruDTO updateGuru(Long id, RequestCreateGuruDTO dto) {
        Guru findById = findById(id);
        findById.setNip(dto.getNip());
        findById.setName(dto.getName());
        findById.setPhone(dto.getPhone());
        findById.setAlamat(dto.getAlamat());
        findById.setMaster(dto.isMaster());
        findById.setDateModified(Constants.getTimestamp());
        Guru guru = guruRepository.save(findById);

        return new ResponseGuruDTO(guru);
    }

    @Override
    public void deleteGuru(Long id) {
        Guru findById = findById(id);
        findById.setDeleted(true);
        guruRepository.save(findById);
    }

}
