package com.pareeksha.blackcat.marvel.dto;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.*;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String errorTitle;
    private String errorCode;
    private String errorMessage;

    public ErrorResponse(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        try{
            JSONObject result = new JSONObject();

            result.put("errorTitle", this.errorTitle);
            result.put("errorCode", this.errorCode);
            result.put("errorMessage", this.errorMessage);

            return result.toString();
        }catch (Exception e){
            return "{\"success\":false, \"exception\":\"" + e.getMessage() + "\"}";
        }
    }
}
