package com.uco.RoomuxApi.RommuxApi.crossCutting.utils;

import org.springframework.stereotype.Service;

@Service
public class UtilDefaultObject {


    private UtilDefaultObject(){

    };

    public static <T> T defaultValue(T valorEntrada, T valorPorDefecto) {
        if (isDefault(valorEntrada)) {
            return valorPorDefecto;
        }
        return valorEntrada;
    }


    private static boolean isDefault(Object valor){
        return valor.equals(null);
    }

}
