package com.pareeksha.blackcat.marvel.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationFormTableDTO {
    String examName;
    String applyUrl;
    Date startDate;
    Date lastDate;
}
