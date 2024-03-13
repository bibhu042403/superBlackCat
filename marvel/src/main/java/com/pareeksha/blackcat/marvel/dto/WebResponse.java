package com.pareeksha.blackcat.marvel.dto;

import lombok.Data;
import org.json.JSONObject;

@Data
public class WebResponse {

    private String status;
    private ErrorResponse error;

    @Override
    public String toString(){
        try{
            JSONObject result = new JSONObject();
            result.put("status", this.status);
            result.put("error", this.error);

            return result.toString();
        }catch (Exception e){
            return "{\"success\":false, \"exception\":\"" + e.getMessage() + "\"}";
        }
    }
}
