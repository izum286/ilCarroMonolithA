package com.telran.ilcarro.service.mapper;


import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.service.model.FilterNode;

public interface MapperService {
    <U> U map(String from, Class to);

    <T> String map(T from);

    FilterDTO map (AddUpdateCarDtoRequest from);
    FilterNode map (FilterDTO toAdd) throws IllegalAccessException;

    //TODO mapper entities to dto


}
