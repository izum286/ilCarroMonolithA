package com.telran.ilcarro.service.exceptions;

import org.springframework.stereotype.Component;

@Component
public class GlobalHandler extends RuntimeException {
    //TODO - need to check if this class is needed at all.

    public void handleEmptyData(String data) {
        if (data == null) {
            throw new EmptyDataException("Empty Data");
        }
    }

}
