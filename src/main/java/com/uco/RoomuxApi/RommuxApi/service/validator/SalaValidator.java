package com.uco.RoomuxApi.RommuxApi.service.validator;

public class SalaValidator {

    private SalaValidator(){

    }

    public static boolean nameLengthIsValid(String name){
        return name.length() <= 15;
    }

}
