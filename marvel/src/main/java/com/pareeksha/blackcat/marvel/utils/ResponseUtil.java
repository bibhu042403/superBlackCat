package com.pareeksha.blackcat.marvel.utils;

import com.pareeksha.blackcat.marvel.constants.ErrorConstants;
import com.pareeksha.blackcat.marvel.dto.ErrorResponse;
import org.springframework.stereotype.Component;
@Component
public class ResponseUtil {

    public ErrorResponse setErrorResponseObject(String errorType){
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setErrorCode(errorType);
        errorResponse.setErrorTitle(ErrorConstants.getErrorTitleMap().get(errorType));
        errorResponse.setErrorMessage(ErrorConstants.getErrorMsgMap().get(errorType));

        return errorResponse;

    }
}
