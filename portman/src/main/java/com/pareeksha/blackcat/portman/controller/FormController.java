package com.pareeksha.blackcat.portman.controller;

import com.pareeksha.blackcat.avenger.facade.WebFacade;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/web/pareeksha")
@CrossOriginResourceSharing(
        allowOrigins = {
                "*"
        },
        allowCredentials = true,
        maxAge = 3200,
        allowHeaders = {
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"
        },
        exposeHeaders = {
                "Security-Token", "Security-Timestamp", "User-Key"
        }
)
class FormController {

    @Autowired
    WebFacade webFacade;

    @POST
    @Path("/updateForm")
    Response updateFormData(@FormParam(value = "source") String source,@FormParam(value = "data") String data){
      return webFacade.saveFormDetails(source,data);
    }



}
