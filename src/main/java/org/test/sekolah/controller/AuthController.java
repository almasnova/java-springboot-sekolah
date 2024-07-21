package org.test.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.sekolah.dto.RequestUserToken;
import org.test.sekolah.dto.ResponseMain;
import org.test.sekolah.dto.ResponseOk;
import org.test.sekolah.entity.Guru;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.exception.DataEmptyOrNullException;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.security.JwtTokenResponse;
import org.test.sekolah.service.AuthenticateService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Validated
public class AuthController {

    @Autowired
    private AuthenticateService authService;

    @PostMapping("/authenticate")
    public ResponseEntity<ResponseMain> createAuthenticationToken(@Valid @RequestBody RequestUserToken authenticationRequest) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            JwtTokenResponse token = authService.authenticate(authenticationRequest);
            status = HttpStatus.OK;
            response.setSuccess(token);
            response.setMessage("Sucess Login");
        } catch (AuthenticationException ae) {
            status = HttpStatus.UNAUTHORIZED;
            response.setFail(ae.getMessage());
        } catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponseMain> logout(@ApiIgnore @AuthenticationPrincipal Guru guru) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            ResponseOk oke = authService.logout(guru);
            status = HttpStatus.OK;
            response.setSuccess(oke);
            response.setMessage("Sucess Logout");
        } catch (AuthenticationException ae) {
            status = HttpStatus.UNAUTHORIZED;
            response.setFail(ae.getMessage());
        } catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

}
