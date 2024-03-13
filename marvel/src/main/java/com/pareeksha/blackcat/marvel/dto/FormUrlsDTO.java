package com.pareeksha.blackcat.marvel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormUrlsDTO {
    String applyUrl;
    String downloadNotification;
    String officialWebsite;
}
