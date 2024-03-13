package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.PareekshaFacade;
import com.pareeksha.blackcat.hunter.facade.DBMgmtFacade;
import com.pareeksha.blackcat.marvel.dto.response.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
@Slf4j
public class LandingPageController {

    @Autowired
    PareekshaFacade pareekshaFacade;

    @Autowired
    DBMgmtFacade dbMgmtFacade;
    @GetMapping(value = "/test")
    public String getStatus(){
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "top/ten/application")
    public List<ApplicationFormTableDTO> getAllFormDetails() {
        return pareekshaFacade.getTopTenApplication();
    }
    @GetMapping(value = "top/ten/admit")
    public List<AdmitCardDTO> getAllAdmitCard(){
        return pareekshaFacade.getTopTenAdmit();
    }
    @GetMapping(value = "top/ten/result")
    public List<ResultDetailsDTO> getAllResult(){
        return pareekshaFacade.getTopTenResult();
    }

    @GetMapping(value = "/getNameDetails")
    public List<FormDetailDTO> getNameDetail(@RequestParam String examBoard, @RequestParam String dept){
        return pareekshaFacade.getFormBasedOnBoardAndDepartment(examBoard, dept);
    }

    @GetMapping(value = "/fetch/application")
    public ApplicationFormDetailsDTO getApplication(@RequestParam String examName) throws IOException, NoSuchAlgorithmException {
        return pareekshaFacade.getApplicationForm(examName);
    }

    @GetMapping(value = "/fetch/admit")
    public AdmitCardDTO admitCardDTO(@RequestParam String examName) throws NoSuchAlgorithmException {
        return pareekshaFacade.getAdmitCard(examName);
    }

    @GetMapping(value = "/fetch/result")
    public ResultDetailsDTO getResult(@RequestParam String examName) throws NoSuchAlgorithmException {
        return pareekshaFacade.getResult(examName);
    }

}
