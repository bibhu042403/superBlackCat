package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.avenger.service.UploadDataService;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UploadDataFacade {
    @Autowired
    UploadDataService uploadDataService;

    public ResponseEntity<String> saveApplicationFormDetails(ApplicationFormDetailsDTO applicationFormDetailsDTO){
       return uploadDataService.saveApplicationAndFormDetails(applicationFormDetailsDTO);
    }

    public void saveAdmitCard(AdmitCardDTO admitCardDTO){
        uploadDataService.saveAdmitCard(admitCardDTO);
    }

    public void saveResult(ResultDetailsDTO resultDetailsDTO){
        uploadDataService.saveResult(resultDetailsDTO);
    }
}
