package com.telran.ilcarro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.ilcarro.model.web.*;
import com.telran.ilcarro.repository.entity.*;
import com.telran.ilcarro.service.model.FilterNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
public class MapperServiceImpl implements MapperService {
    ObjectMapper mapper = new ObjectMapper();

    /**
     * mapped STRING to DTO
     * way: controller->service
     *
     * @param from
     * @param to
     * @param <U>
     * @return Class created from incoming String
     * @author izum286
     */
    public <U> U map(String from, Class to) {
        U res = null;
        try {
            res = (U) mapper.readValue(from, to);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * Mapped ENTITY or DTO to String
     * way: service->controller
     *
     * @param from
     * @return String from Object
     * @author izum286
     */
    public <T> String map(T from) {
        String res = null;
        try {
            res = mapper.writeValueAsString(from);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * mapping method from FullCarDto to FilterDto
     * participated in invoke chain of /upload->save page
     * @param from
     * @return new FilterDto
     * @author izum286
     */
    @Override
    public FilterDTO map(FullCarDTO from) {
        return FilterDTO.builder()
            .make(from.getManufacture())
            .models(from.getModel())
            .years(String.valueOf(from.getYear()))
            .engines(from.getSpecifications().getEngine())
            .fuel(from.getSpecifications().getFuelType())
            .transmissions(from.getSpecifications().getTransmission())
            .wd(from.getSpecifications().getWd())
            .horsepower(String.valueOf(from.getSpecifications().getHp()))
            .torque(String.valueOf(from.getSpecifications().getTorque()))
            .doors(String.valueOf(from.getSpecifications().getDoors()))
            .seats(String.valueOf(from.getSpecifications().getSeats()))
            .classs(from.getSpecifications().getClasz())
            .fuelConsumption(from.getSpecifications().getFuelCons())
                .build();
    }
    /**
     * mapping method from FilterDto to FilterNode
     * participated in invoke chain internal methods of FilterService
     * @param toAdd
     * @return new FilterNode
     * @author izum286
     */
    @Override
    public FilterNode map(FilterDTO toAdd) throws IllegalAccessException {
        ArrayList<FilterNode> nodes = new ArrayList<>();
        Field[] f = toAdd.getClass().getDeclaredFields();
        for (Field field : f) {
            field.setAccessible(true);
            String fieldType = field.getName();
            String fieldValue = (String) field.get(toAdd);
            FilterNode node = new FilterNode();
            node.setType(fieldType);
            node.setValue(fieldValue);
            nodes.add(node);
        }
        //traversing from lowest to highest node
        //and adding lowest node to childList of highest node
        for (int i = nodes.size()-1; i > 0; i--) {
            nodes.get(i-1).getChilds().add(nodes.get(i));
        }
        //return main node
        return nodes.get(0);
    }


    /**
     * @author vitalii_adler
     * mappers for converting an Entity into an DataTransferObject. Ready for use.
     */
    public FilterDTO map(FilterEntity entity) {
        return FilterDTO.builder()
                .make(entity.getMake())
                .models(entity.getModels())
                .years(entity.getYears())
                .engines(entity.getEngines())
                .fuel(entity.getFuel())
                .transmissions(entity.getTransmissions())
                .wd(entity.getWd())
                .horsepower(entity.getHorsepower())
                .torque(entity.getTorque())
                .doors(entity.getDoors())
                .seats(entity.getSeats())
                .classs(entity.getClasss())
                .fuelConsumption(entity.getFuelConsumption())
                .build();
    }

    public LocationDTO map(LocationEntity entity) {
        return LocationDTO.builder()
                .country(entity.getCountry())
                .street(entity.getStreet())
                .city(entity.getCity())
                .state(entity.getState())
                .zip(entity.getZip())
                .lat(entity.getLat())
                .lon(entity.getLon())
                .build();
    }

    public SchedularUsageDTO map(SchedularUsageEntity entity) {
        return SchedularUsageDTO.builder()
                .id(entity.getId())
                .userId(UUID.fromString(entity.getUserId()))
                .carId(UUID.fromString(entity.getCarId()))
                .startDate(entity.getStartDate())
                .endDate((entity.getEndDate()))
                .build();
    }

    public SpecsDTO map(SpecsEntity entity) {
        return SpecsDTO.builder()
                .engine(entity.getEngine())
                .fuelCons(entity.getFuelCons())
                .fuelType(entity.getFuelType())
                .transmission(entity.getTransmission())
                .wd(entity.getWd())
                .hp(entity.getHp())
                .torque(entity.getTorque())
                .doors(entity.getDoors())
                .seats(entity.getSeats())
                .clasz(entity.getClasz())
                .build();
    }


    public ShortCarDTO map(ShortCarEntity entity) {
        return ShortCarDTO.builder()
                .id(entity.getId())
                .model(entity.getModel())
                .manufacture(entity.getManufacture())
                .year(entity.getYear())
                .price(entity.getPrice())
                .image(new ArrayList<>(entity.getImage()))
                .specifications(map(entity.getSpecifications()))
                .location(map(entity.getLocation()))
                .isRented(entity.isRented())
                .build();
    }

    public UserDTO map(UserEntity entity) {
        return UserDTO.builder()
                .id(UUID.fromString(entity.getUuid()))
                .driverLicense(entity.getDriverLicense())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .city(entity.getCity())
                .street(entity.getStreet())
                .phone(entity.getPhone())
                .usages(entity.getUsages()
                        .stream()
                        .map(this::map)
                        .collect(toList()))
                .build();
    }

}
