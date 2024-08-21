package com.uco.RoomuxApi.RommuxApi.crossCutting.exception;

public class RoomuxApiException extends Exception {

    public RoomuxApiException() {
        super();
    }

    public RoomuxApiException(String message) {
        super(message);
    }

    public RoomuxApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoomuxApiException(Throwable cause) {
        super(cause);
    }
}
