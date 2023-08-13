package com.pareeksha.blackcat.avenger.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pareeksha.blackcat.avenger.constants.PareekshaConstant;
import com.pareeksha.blackcat.avenger.util.JmapperUtil;
import com.pareeksha.blackcat.avenger.util.PareekshaInitilizer;
import com.pareeksha.blackcat.avenger.util.PareekshaUtil;
import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import com.pareeksha.blackcat.hunter.entity.ApplicationFormDetails;
import com.pareeksha.blackcat.hunter.entity.FormDetails;
import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import com.pareeksha.blackcat.hunter.facade.DBMgmtFacade;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@Slf4j
public class UploadDataService {

    @Autowired
    DBMgmtFacade dbMgmtFacade;
    @Autowired
    PareekshaInitilizer pareekshaInitilizer;
    static final ObjectMapper objectMapper = new ObjectMapper();

    public void saveApplicationAndFormDetails(ApplicationFormDetailsDTO applicationFormDetailsDTO){

        ApplicationFormDetails applicationFormDetails =  new ApplicationFormDetails();
        constructApplicationForm(applicationFormDetailsDTO, applicationFormDetails);

        saveFormDetails(applicationFormDetailsDTO.getExamName(), applicationFormDetailsDTO.getExamBoard(),
                        applicationFormDetailsDTO.getDepartment());

        if(dbMgmtFacade.saveApplicationFormDetails(applicationFormDetails) !=null)
            log.info("ApplicationForm has been saved successfully!");
    }

    public void saveAdmitCard(AdmitCardDTO admitCardDTO){
        String examId = generateToHash(admitCardDTO.getExamName());
        AdmitCard admitCard = JmapperUtil.constructAdmitCard(admitCardDTO);
        admitCard.setExamId(examId);

        //saving form details for admit card
        saveFormDetails(admitCardDTO.getExamName(), PareekshaConstant.ADMIT_BOARD, PareekshaConstant.ADMIT_DEPT);

        if(dbMgmtFacade.saveAdmitCard(admitCard) != null)
            log.info("Admit card has been saved successfully!");
    }

    public void saveResult(ResultDetailsDTO resultDetailsDTO){
        String examId = generateToHash(resultDetailsDTO.getExamName());
        ResultDetails resultDetails = JmapperUtil.constructResult(resultDetailsDTO);
        resultDetails.setExamId(examId);

        //saving form details for result
        saveFormDetails(resultDetailsDTO.getExamName(), PareekshaConstant.RESULT_BOARD, PareekshaConstant.RESULT_DEPT);

        if(dbMgmtFacade.saveResult(resultDetails) != null)
            log.info("Result details have been saved successfully!");
    }

    public void constructApplicationForm(ApplicationFormDetailsDTO applicationFormDetailsDTO,
                                         ApplicationFormDetails applicationFormDetails){

        try {
            String examId = generateToHash(applicationFormDetailsDTO.getExamName());

            applicationFormDetails.setExamName(applicationFormDetailsDTO.getExamName());
            applicationFormDetails.setExamBoard(applicationFormDetailsDTO.getExamBoard());
            applicationFormDetails.setExamId(examId);

            String feeDetails = objectMapper.writeValueAsString(applicationFormDetailsDTO.getFeeDetailsDTO());
            applicationFormDetails.setFeeDetails(feeDetails);

            String ageLimit = objectMapper.writeValueAsString(applicationFormDetailsDTO.getAgeLimitDTO());
            applicationFormDetails.setAgeLimits(ageLimit);

            String formUrl = objectMapper.writeValueAsString(applicationFormDetailsDTO.getFormUrlsDTO());
            applicationFormDetails.setUrl(formUrl);

            applicationFormDetails.setStartDate(applicationFormDetailsDTO.getStartDate());
            applicationFormDetails.setLastDate(applicationFormDetailsDTO.getLastDate());

            applicationFormDetails.setQualification(applicationFormDetailsDTO.getQualification());
            applicationFormDetails.setExtraField(applicationFormDetailsDTO.getExtraFields());

            applicationFormDetails.setDateModified(new Date());
            applicationFormDetails.setDateCreated(new Date());

        } catch (JsonProcessingException e) {
            log.info("Exception occurred while converting applicationDetail to its DTO ");
            throw new RuntimeException(e);
        }
    }

    public String generateToHash(String examName){
        try{
            return PareekshaUtil.generateHashFromString(examName);
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveFormDetails(String examName, String examBoard, String department){
        FormDetails formDetails = new FormDetails();
        String examId = generateToHash(examName);

        formDetails.setExamId(examId);
        formDetails.setExamName(examName);
        formDetails.setExamBoard(examBoard);
        formDetails.setDepartment(department);

        if(dbMgmtFacade.saveFormDetails(formDetails) != null)
            log.info("Form detail have been saved successfully!");
    }
}

