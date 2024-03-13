package com.pareeksha.blackcat.avenger.facade;

import com.google.gson.GsonBuilder;
import com.pareeksha.blackcat.marvel.constants.ErrorConstants;
import com.pareeksha.blackcat.marvel.dto.response.AdmitCardDTO;
import com.pareeksha.blackcat.marvel.dto.response.ApplicationFormDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.response.ResultDetailsDTO;
import com.pareeksha.blackcat.marvel.utils.StringUtil;
import com.pareeksha.blackcat.marvel.utils.WebAppUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Slf4j
@Component
public class WebFacade {

    @Autowired
    WebAppUtils webAppUtils;
    @Autowired
    UploadDataFacade uploadDataFacade;
    public Response saveFormDetails(String source, String data){
        if(StringUtil.isEmpty(data)){
            return webAppUtils.invalidReqest();
        }

        try {
            if("APPFORM".equalsIgnoreCase(source)){
                return updateApplication(data);
            }else if("ADMIT".equalsIgnoreCase(source)){
                return updateAdmitDetail(data);
            }else if("RESULT".equalsIgnoreCase(source)){
                return updateResult(data);
            }
        }catch (Exception e){
            log.error("Exception occurred while parsing data ",e);
            return webAppUtils.buildErrorResponse("FAILURE", ErrorConstants.SERVER_ERROR);
        }
        return webAppUtils.buildSuccessResponse("SUCCESS");
    }

    public Response updateAdmitDetail(String admitCardData){

        try {
            AdmitCardDTO admitCardDTO =
                    new GsonBuilder().serializeNulls().create().fromJson(admitCardData, AdmitCardDTO.class);
            return uploadDataFacade.saveAdmitCard(admitCardDTO);
        }catch (Exception e){
            log.error("Exception occurred while uploading admit card ",e);
            return webAppUtils.buildErrorResponse("FAILURE", ErrorConstants.SERVER_ERROR);
        }
    }

    public Response updateApplication(String applicationData){

        try {
            ApplicationFormDetailsDTO applicationFormDetailsDTO =
                    new GsonBuilder().serializeNulls().create().fromJson(applicationData, ApplicationFormDetailsDTO.class);
            return uploadDataFacade.saveApplicationFormDetails(applicationFormDetailsDTO);

        }catch (Exception e){
            log.error("Exception occurred while uploading admit card ",e);
            return webAppUtils.buildErrorResponse("FAILURE", ErrorConstants.SERVER_ERROR);
        }

    }

    public Response updateResult(String resultData){

        try {
            ResultDetailsDTO resultDetailsDTO =
                    new GsonBuilder().serializeNulls().create().fromJson(resultData, ResultDetailsDTO.class);
            return uploadDataFacade.saveResult(resultDetailsDTO);
        }catch (Exception e){
            log.error("Exception occurred while uploading admit card ",e);
            return webAppUtils.buildErrorResponse("FAILURE", ErrorConstants.SERVER_ERROR);
        }
    }
}
