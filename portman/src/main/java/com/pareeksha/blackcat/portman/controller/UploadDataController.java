package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.UploadDataFacade;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
@Slf4j
public class UploadDataController {

    @Autowired
    UploadDataFacade uploadDataFacade;

    @PostMapping(value = "/application")
    public ResponseEntity<String> saveApplicationForm(@RequestBody ApplicationFormDetailsDTO applicationFormDetailsDTO){
        return uploadDataFacade.saveApplicationFormDetails(applicationFormDetailsDTO);
    }

    @PostMapping(value = "/admitCard")
    public void saveAdmitCard(@RequestBody AdmitCardDTO admitCardDTO){
        uploadDataFacade.saveAdmitCard(admitCardDTO);
    }

    @PostMapping(value = "/result")
    public void saveResult(@RequestBody ResultDetailsDTO resultDetailsDTO){
        uploadDataFacade.saveResult(resultDetailsDTO);
    }
}
