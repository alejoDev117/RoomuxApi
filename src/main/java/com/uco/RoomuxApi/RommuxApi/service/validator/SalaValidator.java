package com.uco.RoomuxApi.RommuxApi.service.validator;

import com.uco.RoomuxApi.RommuxApi.crossCutting.exception.RoomuxApiException;
import com.uco.RoomuxApi.RommuxApi.crossCutting.utils.UtilText;

public class SalaValidator {

    private SalaValidator(){

    }

    public static boolean nameLengthIsValid(String name){
        return name.length() <= 15;
    }

    public static void nameIsValid(String name) throws RoomuxApiException {
        if(UtilText.containsSpecialCharacters(name)){
            throw new RoomuxApiException("Error, el nombre de la sala no puede contener caracteres especiales");
        }
    }
}
