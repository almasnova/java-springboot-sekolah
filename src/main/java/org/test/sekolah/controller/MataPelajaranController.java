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
import org.test.sekolah.service.MataPelajaranService;
import org.test.sekolah.service.SiswaService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Validated
public class MataPelajaranController {

    @Autowired
    private MataPelajaranService mataPelajaranService;

    @GetMapping("/mata-pelajaran/{id}")
    public ResponseEntity<ResponseMain> findById(@PathVariable("id") Long id) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            ResponseMataPelajaranDTO resp = mataPelajaranService.getById(id);
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

    @PostMapping("/mata-pelajaran/paging")
    public ResponseEntity<ResponseMain> findAllPaging(@RequestParam(value = "search", required = false) String search,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            ResponseCustomPaging<ResponseMataPelajaranDTO> resp = mataPelajaranService.findAllPaging(search, page, size);
            status = HttpStatus.OK;
            response.setSuccess(resp);
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
            e.printStackTrace();
        }
        return ResponseEntity.status(status).body(response);
    }

    @PostMapping("/mata-pelajaran")
    public ResponseEntity<ResponseMain> create(@Valid @RequestBody RequestCreateMataPelajaranDTO dto) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            ResponseMataPelajaranDTO resp = mataPelajaranService.create(dto);
            status = HttpStatus.OK;
            response.setSuccess(resp);
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

    @PutMapping("/mata-pelajaran/{id}")
    public ResponseEntity<ResponseMain> update(@PathVariable("id") Long id,
                                               @Valid @RequestBody RequestCreateMataPelajaranDTO dto) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            ResponseMataPelajaranDTO resp = mataPelajaranService.update(id, dto);
            status = HttpStatus.OK;
            response.setSuccess(resp);
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

    @DeleteMapping(value = "/mata-pelajaran/{id}")
    public ResponseEntity<ResponseMain> delete(@PathVariable("id") Long id) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            mataPelajaranService.deleteMataPelajaran(id);
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