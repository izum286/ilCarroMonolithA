package com.telran.ilcarro.service;


import com.telran.ilcarro.model.web.FilterDTO;
import com.telran.ilcarro.model.web.FullCarDTOResponse;
import com.telran.ilcarro.service.model.FilterNode;

public interface MapperService {
    <U> U map(String from, Class to);

    <T> String map(T from);

    FilterDTO map (FullCarDTOResponse from);
    FilterNode map (FilterDTO toAdd) throws IllegalAccessException;

    //TODO mapper entities to dto


}
