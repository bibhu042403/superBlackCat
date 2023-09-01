package com.pareeksha.blackcat.avenger.service;

import com.pareeksha.blackcat.avenger.constants.PareekshaConstant;
import com.pareeksha.blackcat.hunter.entity.UserDetail;
import com.pareeksha.blackcat.hunter.facade.DBMgmtFacade;
import com.pareeksha.blackcat.marvel.dto.LogInDTO;
import com.pareeksha.blackcat.marvel.dto.RegisterDTO;
import com.pareeksha.blackcat.marvel.enums.UserEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    DBMgmtFacade dbMgmtFacade;

    @Autowired
    EmailService emailService;

    public ResponseEntity<String> registerUser(RegisterDTO registerDTO) {
        Optional<UserDetail> userDetails = dbMgmtFacade.getUserDetail(registerDTO.getUserName());
        if (userDetails.isPresent())
            return new ResponseEntity<>(PareekshaConstant.USER_PRESENT, HttpStatus.CONFLICT);

        String hashPassword = BCrypt.hashpw(registerDTO.getPassWord(), BCrypt.gensalt());
        //Map DTO to user entity
        UserDetail userDetail = buildUserDetail(registerDTO);
        userDetail.setPassword(hashPassword);

        if (dbMgmtFacade.saveUser(userDetail) != null) {
            log.info("userName :: {} has been registered successfully", registerDTO.getUserName());
            sendMailToUserAndOwner(registerDTO);
            return new ResponseEntity<>(PareekshaConstant.REGISTERED, HttpStatus.OK);
        }
        log.info("some issue happened while registering userName :: {}", registerDTO.getUserName());
        return new ResponseEntity<>(PareekshaConstant.REG_FAILED, HttpStatus.BAD_REQUEST);
    }

    public UserDetail buildUserDetail(RegisterDTO registerDTO) {
        UserDetail userDetail = new UserDetail();
        userDetail.setUserName(registerDTO.getUserName());
        userDetail.setFName(registerDTO.getFirstName());
        userDetail.setLName(registerDTO.getSecondName());
        userDetail.setEnable(0);
        userDetail.setUserRole(UserEnum.ADMIN.name());

        return userDetail;
    }

    public void sendMailToUserAndOwner(RegisterDTO registerDTO) {
        String mailBody = MessageFormat.format(PareekshaConstant.REGISTERED_BODY, registerDTO.getFirstName());
        //Sending mail to user about registration
        emailService.sendEmail(registerDTO.getUserName(), PareekshaConstant.REGISTERED_SUB, mailBody);
        //Sending mail to owner about registration
        String ownerMailBody = MessageFormat.format(PareekshaConstant.OWNER_MAIL_BODY, registerDTO.getFirstName(),
                registerDTO.getUserName());
        emailService.sendEmail(PareekshaConstant.OWNER_EMAIL1, PareekshaConstant.OWNER_MAIL_SUB, ownerMailBody);
    }

    public ResponseEntity<String> login(LogInDTO logInDTO){
        Optional<UserDetail> userDetails = dbMgmtFacade.getUserDetail(logInDTO.getUserName());
        if(userDetails.isPresent()){
            UserDetail storedUser = userDetails.get();
            String userPass = BCrypt.hashpw(logInDTO.getPassWord(), storedUser.getPassword());
            if(storedUser.getPassword().equals(userPass)){
                if(storedUser.getEnable() == 1)
                {
                    if(storedUser.getUserRole().equals(UserEnum.ADMIN.name()))
                        return new ResponseEntity<>(UserEnum.ADMIN.name(), HttpStatus.ACCEPTED);
                    return new ResponseEntity<>(UserEnum.LEAD.name(), HttpStatus.ACCEPTED);
                }
                return new ResponseEntity<>(PareekshaConstant.BLOCKED, HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(PareekshaConstant.WRONG_PASS, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(PareekshaConstant.DO_SIGNUP, HttpStatus.CONFLICT);
    }
}
