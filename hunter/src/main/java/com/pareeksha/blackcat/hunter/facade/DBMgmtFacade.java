package com.pareeksha.blackcat.hunter.facade;

import com.pareeksha.blackcat.hunter.entity.AdmitCard;
import com.pareeksha.blackcat.hunter.entity.ApplicationFormDetails;
import com.pareeksha.blackcat.hunter.entity.FormDetails;
import com.pareeksha.blackcat.hunter.entity.ResultDetails;
import com.pareeksha.blackcat.hunter.manager.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class DBMgmtFacade {

    @Autowired
    AdmitCardManagerImpl admitCardManager;

    @Autowired
    ApplicationFormDetailsManagerImpl applicationFormDetailsManager;

    @Autowired
    FormDetailsManagerImpl formDetailsManager;

    @Autowired
    ResultDetailsManagerImpl resultDetailsManager;

    @Autowired
    SystemPropertyManagerImpl systemPropertyManager;

    public static final int dataCount = 10;

    public List<ApplicationFormDetails> getTopTenApplicationForm(){
        return applicationFormDetailsManager.getTopTenForm(dataCount).get();
    }

    public List<AdmitCard> getTopTenAdmitCard(){
        return admitCardManager.getTopTenAdmitCard(dataCount).get();
    }

    public List<ResultDetails> getTopTenResult(){
        return resultDetailsManager.getTopTenResult(dataCount).get();
    }

    public ApplicationFormDetails getApplicationById(String examId){
       Optional<ApplicationFormDetails> applicationFormDetails = applicationFormDetailsManager.findApplicationByExamId(examId);
        return applicationFormDetails.orElse(null);
    }

    public AdmitCard getAdmitCard(String examId){
        Optional<AdmitCard> admitCard = admitCardManager.findAdmitByExamId(examId);
        return admitCard.orElse(null);
    }

    public ResultDetails getResult(String examId){
        Optional<ResultDetails> resultDetails = resultDetailsManager.findResultByExamId(examId);
        return resultDetails.orElse(null);
    }

    public List<FormDetails> getAllFormDetailByExamBoard(String examBoard){
        return formDetailsManager.findAllFormByExamBoard(examBoard);
    }

    public ApplicationFormDetails saveApplicationFormDetails(ApplicationFormDetails applicationFormDetails){
        try {
            return applicationFormDetailsManager.save(applicationFormDetails);
        }catch (Exception e){
            log.info("Exception has occurred while saving application form details");
        }
        return null;
    }

    public FormDetails saveFormDetails(FormDetails formDetails){
        try {
            return formDetailsManager.save(formDetails);
        }catch(Exception e){
            log.info("Exception has occurred while saving form detail ");
        }
        return null;
    }

    public AdmitCard saveAdmitCard(AdmitCard admitCard){
        try{
            return admitCardManager.save(admitCard);
        }catch (Exception e){
            log.info("Exception occurred while saving admit card ", e);
        }
        return null;
    }

    public ResultDetails saveResult(ResultDetails resultDetails){
        try {
            return resultDetailsManager.save(resultDetails);
        }catch (Exception e){
            log.info("Exception occurred while saving result");
        }
        return null;
    }

}
