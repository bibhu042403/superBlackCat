package com.pareeksha.blackcat.avenger.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pareeksha.blackcat.avenger.constants.PareekshaConstant;
import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import com.pareeksha.blackcat.hunter.entity.ApplicationFormDetails;
import com.pareeksha.blackcat.hunter.entity.FormDetails;
import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import com.pareeksha.blackcat.hunter.facade.DBMgmtFacade;
import com.pareeksha.blackcat.marvel.dto.FormUrlsDTO;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormTableDTO;
import com.pareeksha.blackcat.marvel.dto.response.FormDetailDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class PareekshaInitilizer {
    static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<ApplicationFormTableDTO> formTable = new ArrayList<>();
    private static final List<AdmitCardDTO> admitTable = new ArrayList<>();
    private static final List<ResultDetailsDTO> resultTable = new ArrayList<>();
    private static final Map<String,List<FormDetailDTO>> formDetailMapForCentral = new HashMap<>();
    private static final  List<FormDetailDTO> admitCardNameDetailList = new ArrayList<>();
    private static final List<FormDetailDTO> resultNameDetailList = new ArrayList<>();
    private static final Map<String, List<FormDetailDTO>> stateFormDetailMap = new HashMap<>();

    @Autowired
    DBMgmtFacade dbMgmtFacade;

    @PostConstruct
    public void init() {
        initFormTable();
        initAdmitCardTable();
        initResultTable();
        initFormDetailsForCentral();
        initStateFormDetail();
        initAdmitCardName();
        initResultName();
    }

    /**
     * We are preloading top 10 application form data
     * converted to dto which is consumed by frontend
     */
    private void initFormTable() {
        List<ApplicationFormDetails> formList = dbMgmtFacade.getTopTenApplicationForm();

        formList.forEach(formDetails ->{
            try {
                ApplicationFormTableDTO applicationFormTableDTO = buildApplicationFormTableDTO(formDetails);
                formTable.add(applicationFormTableDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Preload top 10 admitCard and convert to DTO
     */
    private void initAdmitCardTable(){
        List<AdmitCard> admitCardList = dbMgmtFacade.getTopTenAdmitCard();
        if(CollectionUtils.isNotEmpty(admitCardList))
            admitCardList.forEach(admitCard ->
                    admitTable.add(JmapperUtil.constructAdmitCardDTO(admitCard)));
    }

    /**
     * Preload top 10 result and convert to DTO
     */
    public void initResultTable(){
        List<ResultDetails> resultDetailsList = dbMgmtFacade.getTopTenResult();

        if(CollectionUtils.isNotEmpty(resultDetailsList))
            resultDetailsList.forEach(resultDetails ->
                   resultTable.add(JmapperUtil.constructResultDTO(resultDetails)));
    }

    public void initFormDetailsForCentral(){
        List<FormDetails> formDetails = dbMgmtFacade.getAllFormDetailByExamBoard(PareekshaConstant.CENTRAL_BOARD);

        formDetails.forEach(formDetail -> {
            String dept = formDetail.getDepartment();
            FormDetailDTO formDetailDTO = buildFormDetailDTO(formDetail);
            formDetailMapForCentral
                    .computeIfAbsent(dept, k -> new ArrayList<>())
                    .add(formDetailDTO);
        });
    }

    public void initStateFormDetail(){
        List<FormDetails> stateFormDetail = dbMgmtFacade.getAllFormDetailByExamBoard(PareekshaConstant.STATE_BOARD);
        stateFormDetail.forEach(formDetails -> {
            String dept = formDetails.getDepartment();
            FormDetailDTO formDetailDTO = buildFormDetailDTO(formDetails);
            stateFormDetailMap
                    .computeIfAbsent(dept, k -> new ArrayList<>())
                    .add(formDetailDTO);
        });
    }
    public void initAdmitCardName(){
        List<FormDetails> stateFormDetail = dbMgmtFacade.getAllFormDetailByExamBoard(PareekshaConstant.ADMIT_BOARD);
        stateFormDetail.forEach(formDetails -> {
            FormDetailDTO formDetailDTO = buildFormDetailDTO(formDetails);
            admitCardNameDetailList.add(formDetailDTO);
        });
    }
    public void initResultName(){
        List<FormDetails> stateFormDetail = dbMgmtFacade.getAllFormDetailByExamBoard(PareekshaConstant.RESULT_BOARD);
        stateFormDetail.forEach(formDetails -> {
            FormDetailDTO formDetailDTO = buildFormDetailDTO(formDetails);
            resultNameDetailList.add(formDetailDTO);
        });
    }

    private FormDetailDTO buildFormDetailDTO(FormDetails formDetails){
        FormDetailDTO formDetailDTO = new FormDetailDTO();

        formDetailDTO.setExamName(formDetails.getExamName());
        return formDetailDTO;
    }

    private ApplicationFormTableDTO buildApplicationFormTableDTO(ApplicationFormDetails applicationFormDetails) throws IOException {
        ApplicationFormTableDTO applicationFormTableDTO = new ApplicationFormTableDTO();
        applicationFormTableDTO.setExamName(applicationFormDetails.getExamName());
        applicationFormTableDTO.setStartDate(applicationFormDetails.getStartDate());

        FormUrlsDTO formUrlsDTO = objectMapper.readValue(applicationFormDetails.getUrl(), FormUrlsDTO.class);
        applicationFormTableDTO.setApplyUrl(formUrlsDTO.getApplyUrl());

        return applicationFormTableDTO;
    }

    public List<ApplicationFormTableDTO> getFormTable(){
        return formTable;
    }
    public List<ResultDetailsDTO> getResultTable(){ return resultTable; }
    public List<AdmitCardDTO> getAdmitTable(){ return admitTable; }
    public List<FormDetailDTO> getCentralFormBasedOnDept(String dept){ return formDetailMapForCentral.get(dept); }
    public List<FormDetailDTO> getStateFormNameDetail(String stateName){ return stateFormDetailMap.get(stateName); }
    public List<FormDetailDTO> getAdmitCardNameDetailList(){ return admitCardNameDetailList; }
    public List<FormDetailDTO> getResultNameDetailList(){ return resultNameDetailList; }

}
