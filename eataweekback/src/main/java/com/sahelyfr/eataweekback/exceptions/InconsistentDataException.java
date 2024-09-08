package com.sahelyfr.eataweekback.exceptions;

public class InconsistentDataException extends Exception {

    private static final String MESSAGE = "Provided data is not consistent : %s from %s VS %s from %s";
    public InconsistentDataException(String variable1, Object o1, String variable2, Object o2) {
        super(String.format(MESSAGE, variable1, o1, variable2, o2));
    }
}
