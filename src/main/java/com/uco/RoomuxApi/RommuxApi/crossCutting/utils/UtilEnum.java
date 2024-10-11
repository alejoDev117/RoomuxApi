package com.uco.RoomuxApi.RommuxApi.crossCutting.utils;

public class UtilEnum {

    public static <E extends Enum<E>> String enumToString(E enumValue) {
        return enumValue != null ? enumValue.name() : "";
    }

    public static <E extends Enum<E>> E stringToEnum(Class<E> enumClass, String value) {
        try {
            return Enum.valueOf(enumClass, value);
        } catch (IllegalArgumentException | NullPointerException e) {
            return null;
        }
    }

    public static <E extends Enum<E>> boolean isValidEnumValue(Class<E> enumClass, String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        // Recorremos los valores del enum para verificar si el string coincide
        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.name().equalsIgnoreCase(value)) {
                return true;  // El valor es válido
            }
        }

        return false;  // No se encontró el valor
    }
}
