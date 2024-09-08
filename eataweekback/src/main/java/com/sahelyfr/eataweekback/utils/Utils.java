package com.sahelyfr.eataweekback.utils;

import com.sahelyfr.eataweekback.exceptions.NullArgumentException;

public class Utils {

    private Utils(){

    }

    public static void checkNotNull(String variable, Object o) throws NullArgumentException {
        if(o == null){
            throw new NullArgumentException(String.format("Variable %s is null", variable));
        }
    }
}
