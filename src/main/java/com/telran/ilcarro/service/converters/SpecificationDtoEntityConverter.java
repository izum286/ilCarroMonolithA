package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.web.SpecsDTO;
import com.telran.ilcarro.repository.entity.SpecsEntity;

public class SpecificationDtoEntityConverter {

    public static SpecsDTO map(SpecsEntity entity){
        return SpecsDTO.builder()
                .clasz(entity.getClasz())
                .doors(entity.getDoors())
                .engine(entity.getEngine())
                .fuelCons(entity.getFuelCons())
                .fuelType(entity.getFuelType())
                .hp(entity.getHp())
                .seats(entity.getSeats())
                .torque(entity.getTorque())
                .transmission(entity.getTransmission())
                .wd(entity.getWd())
                .build();
    }
    public static SpecsEntity map(SpecsDTO dto){
        return SpecsEntity.builder()
                .clasz(dto.getClasz())
                .doors(dto.getDoors())
                .engine(dto.getEngine())
                .fuelCons(dto.getFuelCons())
                .fuelType(dto.getFuelType())
                .hp(dto.getHp())
                .seats(dto.getSeats())
                .torque(dto.getTorque())
                .transmission(dto.getTransmission())
                .wd(dto.getWd())
                .build();
    }
}