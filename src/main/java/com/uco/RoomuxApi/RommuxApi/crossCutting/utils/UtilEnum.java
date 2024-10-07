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
}
