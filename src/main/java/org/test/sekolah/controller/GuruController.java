package org.test.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.test.sekolah.dto.RequestCreateGuruDTO;
import org.test.sekolah.dto.ResponseCustomPaging;
import org.test.sekolah.dto.ResponseGuruDTO;
import org.test.sekolah.dto.ResponseMain;
import org.test.sekolah.entity.Guru;
import org.test.sekolah.exception.AuthenticationException;
import org.test.sekolah.exception.DataEmptyOrNullException;
import org.test.sekolah.exception.DataNotFoundException;
import org.test.sekolah.exception.DataNotValidException;
import org.test.sekolah.service.GuruService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api")
@Validated
public class GuruController {

    @Autowired
    private GuruService guruService;

    @GetMapping("/guru/{id}")
    public ResponseEntity<ResponseMain> findGuruById(@PathVariable("id") Long guruId,
                                                     @ApiIgnore @AuthenticationPrincipal Guru guru) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            if (!guru.isMaster()) throw new DataNotValidException("Account must be Master");
            ResponseGuruDTO resp = guruService.getGuruById(guruId);
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

    @GetMapping("/guru")
    public ResponseEntity<ResponseMain> findDataPribadi(@ApiIgnore @AuthenticationPrincipal Guru guru) {
        HttpStatus status;
        ResponseMain responseMain = new ResponseMain();
        try {
            ResponseGuruDTO resp = guruService.getGuruById(guru.getId());
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


    @PostMapping("/guru/paging")
    public ResponseEntity<ResponseMain> findAllPaging(@RequestParam(value = "search", required = false) String search,
                                                      @RequestParam("page") int page,
                                                      @RequestParam("size") int size,
                                                      @ApiIgnore @AuthenticationPrincipal Guru guru) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            ResponseCustomPaging<ResponseGuruDTO> resp = guruService.findAllPaging(guru, search, page, size);
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

    @PostMapping("/guru")
    public ResponseEntity<ResponseMain> createGuru(@Valid @RequestBody RequestCreateGuruDTO dto,
                                                   @ApiIgnore @AuthenticationPrincipal Guru guru) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            if (!guru.isMaster()) throw new DataNotValidException("Account must be Master");
            ResponseGuruDTO resp = guruService.createGuru(dto);
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

    @PutMapping("/guru/{id}")
    public ResponseEntity<ResponseMain> updateGuru(@PathVariable("id") Long id,
                                                   @Valid @RequestBody RequestCreateGuruDTO dto,
                                                   @ApiIgnore @AuthenticationPrincipal Guru guru) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            if (!guru.isMaster()) throw new DataNotValidException("Account must be Master");
            ResponseGuruDTO resp = guruService.updateGuru(id, dto);
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

    @PutMapping("/guru")
    public ResponseEntity<ResponseMain> updateDataDiri(@Valid @RequestBody RequestCreateGuruDTO dto,
                                                       @ApiIgnore @AuthenticationPrincipal Guru guru) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            ResponseGuruDTO resp = guruService.updateGuru(guru.getId(), dto);
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

    @DeleteMapping(value = "/guru/{id}")
    public ResponseEntity<ResponseMain> deleteGuru(@PathVariable("id") Long id,
                                                   @ApiIgnore @AuthenticationPrincipal Guru guru) {
        ResponseMain response = new ResponseMain();
        HttpStatus status;
        try {
            if (!guru.isMaster()) throw new DataNotValidException("Account must be Master");
            guruService.deleteGuru(id);
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
