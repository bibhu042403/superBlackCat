package com.pareeksha.blackcat.marvel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDTO {
    String firstName;
    String secondName;
    String userName;
    String passWord;
}
