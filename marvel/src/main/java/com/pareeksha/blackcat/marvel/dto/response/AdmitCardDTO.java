package com.pareeksha.blackcat.marvel.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdmitCardDTO {
    int id;
    String examId;
    String examName;
    String downloadUrl;
    Date declaredDate;
    Date dateCreated;
    Date dateModified;
}
