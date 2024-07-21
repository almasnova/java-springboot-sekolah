package org.test.sekolah.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.test.sekolah.dto.RequestCreateGuruDTO;
import org.test.sekolah.dto.ResponseCustomPaging;
import org.test.sekolah.dto.ResponseGuruDTO;
import org.test.sekolah.entity.Guru;

public interface GuruService extends UserDetailsService {

    Guru findByNip(String nip);

    UserDetails findByPhoneAndNipJwt(String phone, String nip);

    ResponseGuruDTO getGuruById(Long guruId);

    ResponseCustomPaging<ResponseGuruDTO> findAllPaging(Guru guru, String search, int page, int size);

    Guru findById(Long guruId);

    ResponseGuruDTO createGuru(RequestCreateGuruDTO dto);

    ResponseGuruDTO updateGuru(Long id, RequestCreateGuruDTO dto);

    void deleteGuru(Long id);

    void setUserLogin(Guru guru);
}
