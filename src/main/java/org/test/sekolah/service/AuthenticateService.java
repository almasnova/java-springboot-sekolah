package org.test.sekolah.service;

import org.test.sekolah.dto.RequestUserToken;
import org.test.sekolah.dto.ResponseOk;
import org.test.sekolah.entity.Guru;
import org.test.sekolah.security.JwtTokenResponse;

public interface AuthenticateService {

    JwtTokenResponse authenticate(RequestUserToken authenticationRequest);

    ResponseOk logout(Guru guru);
}
