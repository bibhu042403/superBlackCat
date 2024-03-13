package com.pareeksha.blackcat.marvel.utils;

import com.google.gson.Gson;
import com.pareeksha.blackcat.marvel.constants.ErrorConstants;
import com.pareeksha.blackcat.marvel.constants.WebAppConstants;
import com.pareeksha.blackcat.marvel.dto.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;

@Component
public class WebAppUtils {

    @Autowired
    ResponseUtil responseUtil;
    public Response invalidReqest(){
        WebResponse webResponse = new WebResponse();
        webResponse.setStatus(WebAppConstants.ERROR);
        webResponse.setError(responseUtil.setErrorResponseObject(ErrorConstants.INVALID_REQUEST));
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        response.entity(new Gson().toJson(webResponse));
        return response.build();
    }

    public Response buildErrorResponse(String status, String errorConstant){
        WebResponse webResponse = new WebResponse();
        webResponse.setStatus(status);
        webResponse.setError(responseUtil.setErrorResponseObject(errorConstant));
        Response.ResponseBuilder response = Response.status(Response.Status.BAD_REQUEST);
        response.entity(new Gson().toJson(webResponse));
        return response.build();
    }

    public Response buildSuccessResponse(String status){
        WebResponse webResponse = new WebResponse();
        webResponse.setStatus(status);
        Response.ResponseBuilder response = Response.status(Response.Status.OK);
        response.entity(new Gson().toJson(webResponse));
        return response.build();
    }
}
