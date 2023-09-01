package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.UserFacade;
import com.pareeksha.blackcat.marvel.dto.LogInDTO;
import com.pareeksha.blackcat.marvel.dto.RegisterDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserController {

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

}
