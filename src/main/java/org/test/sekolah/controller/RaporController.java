package org.test.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.sekolah.dto.*;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.exception.DataEmptyOrNullException;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.service.RaporService;
import org.test.sekolah.service.SiswaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Validated
public class RaporController {

    @Autowired
    private RaporService raporService;

    @PostMapping("/rapor/{siswaId}")
    public ResponseEntity<ResponseMain> findRapor(@PathVariable("siswaId") Long siswaId,
                                                  @Valid @RequestBody RequestRaporSiswaDTO dto) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            ResponseRaporSiswaDTO resp = raporService.getBySiswaId(siswaId, dto);
            status = HttpStatus.OK;
            responseMain.setSuccess(resp);
            responseMain.setMessage("Success");
        } catch (DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            responseMain.setFail(de.getMessage());
            de.printStackTrace();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMain.setFail(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(status).body(responseMain);
    }

    @PostMapping("/create-rapor/{siswaId}")
    public ResponseEntity<ResponseMain> createRapor(@PathVariable("siswaId") Long siswaId,
                                                    @Valid @RequestBody RequestCreateRaporSiswaDTO dto) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            raporService.createRapor(siswaId, dto);
            status = HttpStatus.OK;
            response.setMessage("Success create Nilai rapor");
        } catch (AuthenticationException ae) {
            status = HttpStatus.UNAUTHORIZED;
            response.setFail(ae.getMessage());
            ae.printStackTrace();
        } catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
            de.printStackTrace();
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.status(status).body(response);
    }

}
