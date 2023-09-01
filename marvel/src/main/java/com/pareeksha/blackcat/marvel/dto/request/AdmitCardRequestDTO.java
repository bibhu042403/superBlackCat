package com.pareeksha.blackcat.marvel.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdmitCardRequestDTO {
    String examId;
}
