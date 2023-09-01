package com.pareeksha.blackcat.marvel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeDetailsDTO {
    Integer female;
    Integer general;
    Integer obc;
    Integer ews;
    Integer stSc;
    Integer pwdObc;
    Integer correctionOne;
    Integer correctionTwo;
    String modeOfPayment;
}
