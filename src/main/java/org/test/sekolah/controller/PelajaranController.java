package org.test.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.sekolah.dto.ResponseMain;
import org.test.sekolah.entity.Kelas;
import org.test.sekolah.entity.Pelajaran;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.exception.DataEmptyOrNullException;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.service.KelasService;
import org.test.sekolah.service.PelajaranService;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Validated
public class PelajaranController {

    @Autowired
    private PelajaranService pelajaranService;

    @GetMapping("/pelajaran/{id}")
    public ResponseEntity<ResponseMain> findById(@PathVariable("id") Long id) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            Pelajaran resp = pelajaranService.getById(id);
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

    @GetMapping("/pelajaran/list")
    public ResponseEntity<ResponseMain> findList() {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            List<Pelajaran> resp = pelajaranService.getListPelajaran();
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

    @PostMapping("/pelajaran")
    public ResponseEntity<ResponseMain> create(@RequestParam String pelajaranName) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            pelajaranService.createPelajaran(pelajaranName);
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

    @PutMapping("/pelajaran/{id}")
    public ResponseEntity<ResponseMain> update(@PathVariable("id") Long id,
                                               @RequestParam String pelajaranName) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            pelajaranService.updatePelajaran(id, pelajaranName);
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

    @DeleteMapping(value = "/pelajaran/{id}")
    public ResponseEntity<ResponseMain> delete(@PathVariable("id") Long id) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            pelajaranService.deletePelajaran(id);
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
