package com.pareeksha.blackcat.marvel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgeLimitDTO {
    Integer female;
    Integer general;
    Integer stSc;
    Integer obc;
    Integer pwdUnReserved;
    Integer pwdObc;
    Integer exServiceMen;
}
