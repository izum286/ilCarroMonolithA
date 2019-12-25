package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.FullCarDTOResponse;
import com.telran.ilcarro.repository.entity.FullCarEntity;

//TODO implement new logic according new API
public class FullCarDtoEntityConverter {

    public static FullCarDTOResponse map(FullCarEntity entity) {
        return new FullCarDTOResponse();
    }

    public static FullCarEntity map(FullCarDTOResponse dto) {
        return new FullCarEntity();
    }
}
