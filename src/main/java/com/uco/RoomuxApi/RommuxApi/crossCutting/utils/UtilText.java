package com.uco.RoomuxApi.RommuxApi.crossCutting.utils;

public class UtilText {

    private static final String DEFAULT_TEXT_VALUE = "";

    private UtilText() {}

    public static String getDefaultTextValue() {
        return DEFAULT_TEXT_VALUE;
    }

    public static boolean containsNumbers(String text) {
        return text != null && text.matches(".*\\d.*");
    }

    public static boolean containsSpecialCharacters(String text) {
        return text != null && text.matches(".*[^a-zA-Z\\d\\s].*");
    }
}
