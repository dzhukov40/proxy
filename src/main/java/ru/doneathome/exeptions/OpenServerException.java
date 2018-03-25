package ru.doneathome.exeptions;

public class OpenServerException extends Exception {

    public OpenServerException(String message) {
        super(message);
    }

    public OpenServerException(Throwable cause) {
        super(cause);
    }



}
