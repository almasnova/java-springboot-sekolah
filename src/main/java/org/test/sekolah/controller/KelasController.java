package org.test.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.sekolah.dto.RequestCreateSiswaDTO;
import org.test.sekolah.dto.ResponseCustomPaging;
import org.test.sekolah.dto.ResponseMain;
import org.test.sekolah.dto.ResponseSiswaDTO;
import org.test.sekolah.entity.Kelas;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.exception.DataEmptyOrNullException;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.service.KelasService;
import org.test.sekolah.service.SiswaService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
public class KelasController {

    @Autowired
    private KelasService kelasService;

    @GetMapping("/kelas/{id}")
    public ResponseEntity<ResponseMain> findById(@PathVariable("id") Long id) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            Kelas resp = kelasService.getById(id);
            status = HttpStatus.OK;
            responseMain.setSuccess(resp);
            responseMain.setMessage("Success");
        } catch (DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            responseMain.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMain.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(responseMain);
    }

    @GetMapping("/kelas/list")
    public ResponseEntity<ResponseMain> findList() {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            List<Kelas> resp = kelasService.getListKelas();
            status = HttpStatus.OK;
            responseMain.setSuccess(resp);
            responseMain.setMessage("Success");
        } catch (DataNotFoundException de) {
            status = HttpStatus.BAD_REQUEST;
            responseMain.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMain.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(responseMain);
    }

    @PostMapping("/kelas")
    public ResponseEntity<ResponseMain> create(@RequestParam int tingkatKelas, @RequestParam String kelasName) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            kelasService.createKelas(tingkatKelas, kelasName);
            status = HttpStatus.OK;
            response.setMessage("Success");
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

    @PutMapping("/kelas/{id}")
    public ResponseEntity<ResponseMain> update(@PathVariable("id") Long id,
                                               @RequestParam int tingkatKelas,
                                               @RequestParam String kelasName) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            kelasService.updateKelas(id, tingkatKelas, kelasName);
            status = HttpStatus.OK;
            response.setMessage("Success");
        } catch (AuthenticationException ae) {
            ae.printStackTrace();
            status = HttpStatus.UNAUTHORIZED;
            response.setFail(ae.getMessage());
        } catch (DataEmptyOrNullException | DataNotValidException | DataNotFoundException de) {
            de.printStackTrace();
            status = HttpStatus.BAD_REQUEST;
            response.setFail(de.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setFail(e.getMessage());
        }
        return ResponseEntity.status(status).body(response);
    }

    @DeleteMapping(value = "/kelas/{id}")
    public ResponseEntity<ResponseMain> delete(@PathVariable("id") Long id) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            kelasService.deleteKelas(id);
            status = HttpStatus.OK;
            response.setMessage("Success");
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
