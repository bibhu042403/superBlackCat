package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.UploadDataFacade;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadDataController {

    @Autowired
    UploadDataFacade uploadDataFacade;

    @PostMapping(value = "/application")
    public void saveApplicationForm(@RequestBody ApplicationFormDetailsDTO applicationFormDetailsDTO){
        uploadDataFacade.saveApplicationFormDetails(applicationFormDetailsDTO);
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
