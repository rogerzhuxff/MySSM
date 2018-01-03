package com.demo.common;

import java.util.regex.Pattern;

public class StringUtils {

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    
    public static boolean isNumeric(String str) {
    	
    		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  
    		
        return pattern.matcher(str).matches();  
    }
}