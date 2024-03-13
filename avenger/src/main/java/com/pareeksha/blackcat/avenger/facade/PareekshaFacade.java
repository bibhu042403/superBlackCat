package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.avenger.service.PareekshaService;
import com.pareeksha.blackcat.marvel.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Component
public class PareekshaFacade {
    @Autowired
    PareekshaService pareekshaService;
    public List<ApplicationFormTableDTO> getTopTenApplication(){
        return pareekshaService.getFormTable();
    }

    public List<AdmitCardDTO> getTopTenAdmit(){
        return pareekshaService.getAdmitTable();
    }
    public List<ResultDetailsDTO> getTopTenResult(){
        return pareekshaService.getResultTable();
    }

    public List<FormDetailDTO> getFormBasedOnBoardAndDepartment(String examBoard, String department){
        return pareekshaService.getFormDetailBoardAndDept(examBoard, department);
    }

    public ApplicationFormDetailsDTO getApplicationForm(String examName) throws IOException, NoSuchAlgorithmException {
        return pareekshaService.getApplicationDetails(examName);
    }

    public AdmitCardDTO getAdmitCard(String examName) throws NoSuchAlgorithmException {
        return pareekshaService.getAdmitCard(examName);
    }

    //get result
    public ResultDetailsDTO getResult(String examName) throws NoSuchAlgorithmException {
        return pareekshaService.getResult(examName);
    }

    public List<ApplicationFormDetailsDTO> getAllApp(){
        return pareekshaService.getAllApp();
    }

}
