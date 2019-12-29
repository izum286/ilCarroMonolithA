package com.telran.ilcarro.service.converters;

import com.telran.ilcarro.model.car.SchedularUsageDTO;
import com.telran.ilcarro.repository.entity.SchedularUsageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SchedularUsageListDtoEntityConverter {

    public static SchedularUsageDTO map(SchedularUsageEntity entity){
        return SchedularUsageDTO.builder()
                .carId(UUID.fromString(entity.getCarId()))
                .endDate(entity.getEndDate())
                .startDate(entity.getStartDate())
                .id(entity.getId())
                .userId(entity.getUserId())
                .build();
    }

    public static SchedularUsageEntity map(SchedularUsageDTO dto){
        return SchedularUsageEntity.builder()
                .carId(dto.getCarId().toString())
                .endDate(dto.getEndDate())
                .startDate(dto.getStartDate())
                .id(dto.getId())
                .userId(dto.getUserId())
                .build();
    }

    public static List<SchedularUsageDTO> mapListToDTO(List<SchedularUsageEntity> entities){
        List<SchedularUsageDTO> res =  new ArrayList<>();
        for(SchedularUsageEntity entity : entities){
            res.add(SchedularUsageListDtoEntityConverter.map(entity));
        }
        return res;
    }

    public static List<SchedularUsageEntity> mapListToEntities(List<SchedularUsageDTO> dtos){
        List<SchedularUsageEntity> res =  new ArrayList<>();
        for(SchedularUsageDTO dto : dtos){
            res.add(SchedularUsageListDtoEntityConverter.map(dto));
        }
        return res;
    }
}
