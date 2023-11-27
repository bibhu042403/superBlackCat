package com.pareeksha.blackcat.avenger.facade;

import com.pareeksha.blackcat.avenger.service.UserService;
import com.pareeksha.blackcat.marvel.dto.LogInDTO;
import com.pareeksha.blackcat.marvel.dto.RegisterDTO;
import com.pareeksha.blackcat.marvel.dto.response.UserPermitDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserFacade {

    @Autowired
    UserService userService;

    public ResponseEntity<String> registerUser(RegisterDTO registerDTO){
        if(validateUser(registerDTO))
            return userService.registerUser(registerDTO);
        return new ResponseEntity<>("Please Fill all detail", HttpStatus.BAD_REQUEST);
    }

    private boolean validateUser(RegisterDTO registerDTO){

        return StringUtils.isNotEmpty(registerDTO.getFirstName())
                && StringUtils.isNotEmpty(registerDTO.getSecondName())
                && StringUtils.isNotEmpty(registerDTO.getUserName())
                && StringUtils.isNotEmpty(registerDTO.getPassWord());
    }

    public ResponseEntity<String> login(LogInDTO logInDTO){
        if(validateLogin(logInDTO)) {
            log.info("login requested userName :: {}", logInDTO.getUserName());
            return userService.login(logInDTO);
        }
        return new ResponseEntity<>("Please fill username and password", HttpStatus.CONFLICT);
    }

    private boolean validateLogin(LogInDTO logInDTO){

        return StringUtils.isNotEmpty(logInDTO.getUserName())
                && StringUtils.isNotEmpty(logInDTO.getPassWord());
    }

    public List<UserPermitDTO> getAllOnHoldUser(){
        return userService.getAllOnHoldUser();
    }
}
