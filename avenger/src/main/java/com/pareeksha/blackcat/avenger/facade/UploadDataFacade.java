package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;

import javax.ws.rs.core.Response;

public interface UploadDataFacade {

    Response saveApplicationFormDetails(ApplicationFormDetailsDTO applicationFormDetailsDTO);

    Response saveAdmitCard(AdmitCardDTO admitCardDTO);

    Response saveResult(ResultDetailsDTO resultDetailsDTO);
}
