package com.uco.RoomuxApi.RommuxApi.crossCutting.utils;

public class UtilEmail {
    private static final String DEFAULT_VALUE_MAIL ="null.1234@uco.net.co";
    private static final String EMAIL_REGEX = "^[a-zA-Z]+\\.[a-zA-Z]+\\d{4}@uco\\.net\\.co$";
    private UtilEmail(){

    }

    public static String getDefaultValueMail(){
        return DEFAULT_VALUE_MAIL;
    }

    public static boolean emailIsValid(String email){
        return email.matches(EMAIL_REGEX);
    }


}

