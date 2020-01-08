package com.telran.ilcarro.service.unused;


import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.CarStatDto;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.CarStatEntity;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.OwnerEntity;
import com.telran.ilcarro.service.model.FilterNode;

@Deprecated
public interface MapperService {
    <U> U map(String from, Class to);
    <T> String map(T from);
    FilterDTO map (AddUpdateCarDtoRequest from);
    FullCarDTOResponse map (FullCarEntity from);
    FilterNode map (FilterDTO toAdd) throws IllegalAccessException;
    OwnerEntity map(OwnerDtoResponse dto);
    OwnerDtoResponse map(OwnerEntity entity);
    CarStatEntity map(CarStatDto dto);
    CarStatDto map(CarStatEntity entity);


    //TODO mapper entities to dto


}
