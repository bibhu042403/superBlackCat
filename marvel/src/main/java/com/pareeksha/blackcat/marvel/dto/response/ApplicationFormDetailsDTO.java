package com.pareeksha.blackcat.marvel.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pareeksha.blackcat.marvel.dto.AgeLimitDTO;
import com.pareeksha.blackcat.marvel.dto.FeeDetailsDTO;
import com.pareeksha.blackcat.marvel.dto.FormUrlsDTO;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationFormDetailsDTO {
    String examId;
    String examName;
    String examBoard;
    String department;
    @JsonProperty("feeDetails")
    FeeDetailsDTO feeDetailsDTO;
    @JsonProperty("ageLimit")
    AgeLimitDTO ageLimitDTO;
    @JsonProperty("formUrls")
    FormUrlsDTO formUrlsDTO;
    Date startDate;
    Date lastDate;
    String qualification;
    String extraFields;
}
