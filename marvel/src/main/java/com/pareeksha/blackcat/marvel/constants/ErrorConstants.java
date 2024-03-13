package com.pareeksha.blackcat.marvel.constants;

import java.util.HashMap;
import java.util.Map;

public class ErrorConstants {

    public static final String INVALID_REQUEST = "INVALID_REQUEST";
    public static final String SERVER_ERROR = "SERVER_ERROR";

    public static final Map<String, String> getErrorTitleMap(){
        Map<String, String> errorTypes = new HashMap<>();

        errorTypes.put(INVALID_REQUEST, "Invalid Request");

        return errorTypes;
    }

    public static final Map<String, String> getErrorMsgMap(){
        Map<String, String> errorTypes = new HashMap<>();

        errorTypes.put(INVALID_REQUEST, "Invalid Request");

        return errorTypes;
    }
}
