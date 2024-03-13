package com.pareeksha.blackcat.marvel.utils;

public class StringUtil {

    public static boolean isEmpty(String str){
        if(str == null) return true;
        return "".equals(str.trim());
    }
}
