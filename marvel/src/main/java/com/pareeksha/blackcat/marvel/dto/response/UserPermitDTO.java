package com.pareeksha.blackcat.marvel.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPermitDTO {
    String fName;
    String userName;
}
