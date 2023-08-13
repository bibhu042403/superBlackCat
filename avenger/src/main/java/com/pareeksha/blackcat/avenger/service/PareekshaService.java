package com.pareeksha.blackcat.avenger.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pareeksha.blackcat.avenger.constants.PareekshaConstant;
import com.pareeksha.blackcat.avenger.util.JmapperUtil;
import com.pareeksha.blackcat.avenger.util.PareekshaInitilizer;
import com.pareeksha.blackcat.avenger.util.PareekshaUtil;
import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import com.pareeksha.blackcat.hunter.entity.ApplicationFormDetails;
import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import com.pareeksha.blackcat.hunter.facade.DBMgmtFacade;
import com.pareeksha.blackcat.marvel.dto.AgeLimitDTO;
import com.pareeksha.blackcat.marvel.dto.FeeDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.FormUrlsDTO;
import com.pareeksha.blackcat.marvel.dto.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@Slf4j
public class PareekshaService {

    @Autowired
    DBMgmtFacade dbMgmtFacade;
    @Autowired
    PareekshaInitilizer pareekshaInitilizer;

    static final ObjectMapper objectMapper = new ObjectMapper();

    public List<ApplicationFormTableDTO> getFormTable(){
        return pareekshaInitilizer.getFormTable();
    }

    public List<AdmitCardDTO> getAdmitTable(){
        return pareekshaInitilizer.getAdmitTable();
    }

    public List<ResultDetailsDTO> getResultTable(){
        return pareekshaInitilizer.getResultTable();
    }

    public List<FormDetailDTO> getFormDetailBoardAndDept(String examBoard, String dept){
        if(PareekshaConstant.CENTRAL_BOARD.equalsIgnoreCase(examBoard))
            return pareekshaInitilizer.getCentralFormBasedOnDept(dept);
        else if(PareekshaConstant.ADMIT_BOARD.equalsIgnoreCase(examBoard))
            return pareekshaInitilizer.getAdmitCardNameDetailList();
        else if(PareekshaConstant.RESULT_BOARD.equalsIgnoreCase(examBoard))
            return pareekshaInitilizer.getResultNameDetailList();
        else
            return pareekshaInitilizer.getStateFormNameDetail(dept);
    }

    public ApplicationFormDetailsDTO getApplicationDetails(String examName) throws IOException, NoSuchAlgorithmException {
        String examId = PareekshaUtil.generateHashFromString(examName);
        ApplicationFormDetails applicationFormDetails = dbMgmtFacade.getApplicationById(examId);

        return constructApplicationFormDTO(applicationFormDetails);
    }

    public ApplicationFormDetailsDTO constructApplicationFormDTO(ApplicationFormDetails applicationFormDetails) throws IOException {

        ApplicationFormDetailsDTO applicationFormDetailsDTO = new ApplicationFormDetailsDTO();

        applicationFormDetailsDTO.setExamName(applicationFormDetails.getExamName());
        applicationFormDetailsDTO.setExamBoard(applicationFormDetails.getExamBoard());
        applicationFormDetailsDTO.setStartDate(applicationFormDetails.getStartDate());
        applicationFormDetailsDTO.setLastDate(applicationFormDetails.getLastDate());
        applicationFormDetailsDTO.setQualification(applicationFormDetails.getQualification());
        applicationFormDetailsDTO.setExtraFields(applicationFormDetails.getExtraField());

        applicationFormDetailsDTO.setAgeLimitDTO(objectMapper
                .readValue(applicationFormDetails.getAgeLimits(), AgeLimitDTO.class));
        applicationFormDetailsDTO.setFeeDetailsDTO(objectMapper
                .readValue(applicationFormDetails.getFeeDetails(), FeeDetailsDTO.class));
        applicationFormDetailsDTO.setFormUrlsDTO(objectMapper
                .readValue(applicationFormDetails.getUrl(), FormUrlsDTO.class));

        return applicationFormDetailsDTO;
    }

    public AdmitCardDTO getAdmitCard(String examName) throws NoSuchAlgorithmException {
        String examId = PareekshaUtil.generateHashFromString(examName);
        AdmitCard admitCard = dbMgmtFacade.getAdmitCard(examId);
        return JmapperUtil.constructAdmitCardDTO(admitCard);
    }

    public ResultDetailsDTO getResult(String examName) throws NoSuchAlgorithmException {
        String examId = PareekshaUtil.generateHashFromString(examName);
        ResultDetails resultDetails = dbMgmtFacade.getResult(examId);
        return JmapperUtil.constructResultDTO(resultDetails);
    }
}
