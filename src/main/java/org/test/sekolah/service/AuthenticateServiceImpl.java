package org.test.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.test.sekolah.bean.BCryptPasswordEncoding;
import org.test.sekolah.dto.RequestUserToken;
import org.test.sekolah.dto.ResponseOk;
import org.test.sekolah.entity.Guru;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.security.JwtTokenResponse;
import org.test.sekolah.security.JwtTokenUtil;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private GuruService guruService;

    @Autowired
    private BCryptPasswordEncoding bCryptPasswordEncoding;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public JwtTokenResponse authenticate(RequestUserToken authenticationRequest) {
        Guru guru = guruService.findByNip(authenticationRequest.getNip());
        if (!guru.isEnabled()) throw new AuthenticationException("User is locked");
        if (!guru.isAccountNonLocked()) throw new AuthenticationException("User is locked or not validated");
        if (!bCryptPasswordEncoding.passwordEncoder().matches(authenticationRequest.getPassword(), guru.getPassword()))
            throw new AuthenticationException("Username or password is invalid");

        String token = jwtTokenUtil.generateToken(guru, Boolean.FALSE);
        loginSuccess(guru);
        return new JwtTokenResponse(token);
    }

    private void loginSuccess(Guru guru) {
        guruService.setUserLogin(guru);
    }

    @Override
    public ResponseOk logout(Guru guru) {
        return new ResponseOk("Success");
    }
}