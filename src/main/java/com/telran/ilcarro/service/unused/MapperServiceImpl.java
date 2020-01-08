package com.telran.ilcarro.service.unused;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.car.probably_unused.LocationDTO;
import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.model.car.probably_unused.SpecsDTO;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.entity.*;
import com.telran.ilcarro.service.converters.PricePerDayDtoEntityCoverter;
import com.telran.ilcarro.service.model.FilterNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

@Deprecated
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
     *
     * @param from
     * @return new FilterDto
     * @author izum286
     */
    @Override
    public FilterDTO map(AddUpdateCarDtoRequest from) {
        return FilterDTO.builder()
                .make(from.getMake())
                .models(from.getModel())
                .years(String.valueOf(from.getYear()))
                .engines(from.getEngine())
                .fuel(from.getFuel())
                .transmissions(from.getGear())
                .wd(from.getWheelsDrive())
                .horsepower(String.valueOf(from.getHorsePower()))
                .torque(String.valueOf(from.getTorque()))
                .doors(String.valueOf(from.getDoors()))
                .seats(String.valueOf(from.getSeats()))
                .classs(from.getCarClass())
                .fuelConsumption(String.valueOf(from.getFuelConsumption()))
                .build();
    }

    @Override
    public FullCarDTOResponse map(FullCarEntity from) {
        return FullCarDTOResponse.builder()
                .serialNumber(from.getSerialNumber())
                .make(from.getMake())
                .model(from.getModel())
                .year(String.valueOf(from.getYear()))
                .engine(from.getEngine())
                .fuel(from.getFuel())
                .gear(from.getGear())
                .wheelsDrive(from.getWheelsDrive())
                .doors(from.getDoors())
                .seats(from.getSeats())
                .fuelConsumption(from.getFuelConsumption())
 //               .features(from.getFeatures())
                .carClass(from.getCarClass())
  //              .simplePricePerDay(Float.valueOf(from.getPricePerDaySimple()))
                .distanceIncluded(from.getDistanceIncluded())
                .about(from.getAbout())
 //               .pickUpPlace(from.getPickUpPlace())
//                .imageUrl(from.getImage())
//                .owner(from.getOwner())
//                .bookedPeriodDto(from.getBookedPeriods())
//                .statisticsDto(from.getStatistics())
        .build();
    }


    /**
     * mapping method from FilterDto to FilterNode
     * participated in invoke chain internal methods of FilterService
     *
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
        for (int i = nodes.size() - 1; i > 0; i--) {
            nodes.get(i - 1).getChilds().add(nodes.get(i));
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
                .userId(entity.getUserId())
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
                .price_per_day(PricePerDayDtoEntityCoverter.map(entity.getPrice_per_day()))
                .image(new ArrayList<>(entity.getImage()))
                .specifications(map(entity.getSpecifications()))
                .location(map(entity.getLocation()))
                .isRented(entity.isRented())
                .build();
    }

    /**
     * CarStatEntity ---> CarStatDto
     * @author Gor Aleks
     * 03.01.2020
     */
    public CarStatDto map(CarStatEntity entity){
        return CarStatDto.builder()
                .rating(entity.getRating())
                .trips(entity.getTrips())
                .build();
    }

    /**
     * CarStatDto ---> CarStatEntity
     * @author Gor Aleks
     * 03.01.2020
     */
    public CarStatEntity map(CarStatDto dto){
        return CarStatEntity.builder()
                .rating(dto.getRating())
                .trips(dto.getTrips())
                .build();
    }

    /**
     * OwnerEntity ---> OwnerDtoResponse
     * @author Gor Aleks
     * 03.01.2020
     */
    public OwnerDtoResponse map(OwnerEntity entity){
        return OwnerDtoResponse.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .registrationDate(entity.getRegistrationDate())
                .build();
    }

    /**
     * OwnerDtoResponse ---> OwnerEntity
     * @author Gor Aleks
     * 03.01.2020
     */
    public OwnerEntity map(OwnerDtoResponse dto){
        return OwnerEntity.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .registrationDate(dto.getRegistrationDate())
                .build();
    }
}

