package com.telran.ilcarro.handler;

import com.telran.ilcarro.exception.EmptyDataException;
import org.springframework.stereotype.Component;

@Component
public class GlobalHandler extends RuntimeException {

    public void handleEmptyData(String data) {
        if (data == null) {
            throw new EmptyDataException("Empty Data");
        }
    }

}
