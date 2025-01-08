package com.sahelyfr.eataweekback.infrastructure.utils;

import com.sahelyfr.eataweekback.application.exceptions.NullArgumentException;

public class Utils {

    private Utils() {

    }

    public static void checkNotNull(String variable, Object o) throws NullArgumentException {
        if (o == null) {
            throw new NullArgumentException(String.format("Variable %s is null", variable));
        }
    }
}
