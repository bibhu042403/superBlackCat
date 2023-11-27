package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.UserFacade;
import com.pareeksha.blackcat.marvel.dto.LogInDTO;
import com.pareeksha.blackcat.marvel.dto.RegisterDTO;
import com.pareeksha.blackcat.marvel.dto.response.UserPermitDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class AdminController {

    @Autowired
    UserFacade userFacade;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO){
        return userFacade.registerUser(registerDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LogInDTO logInDTO){
        return userFacade.login(logInDTO);
    }

    @GetMapping("/get/registered/user")
    public List<UserPermitDTO> getAllRegisteredUser(){
        return userFacade.getAllOnHoldUser();
    }

//    @PostMapping("/approve/user")
//    public ResponseEntity<String> approveUser(@RequestParam String userName){
//
//    }
 }
