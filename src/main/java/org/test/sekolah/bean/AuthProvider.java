package org.test.sekolah.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Component;
import org.test.sekolah.service.GuruService;

@Component
public class AuthProvider {

    @Autowired
    private GuruService guruService;

    @Autowired
    private BCryptPasswordEncoding bCryptPasswordEncoding;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(guruService);
        auth.setPasswordEncoder(bCryptPasswordEncoding.passwordEncoder());
        return auth;
    }
}
