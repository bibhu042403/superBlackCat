package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.avenger.service.UploadDataService;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UploadDataFacade {
    @Autowired
    UploadDataService uploadDataService;

    public void  saveApplicationFormDetails(ApplicationFormDetailsDTO applicationFormDetailsDTO){
        uploadDataService.saveApplicationAndFormDetails(applicationFormDetailsDTO);
    }

    public void saveAdmitCard(AdmitCardDTO admitCardDTO){
        uploadDataService.saveAdmitCard(admitCardDTO);
    }

    public void saveResult(ResultDetailsDTO resultDetailsDTO){
        uploadDataService.saveResult(resultDetailsDTO);
    }
}
