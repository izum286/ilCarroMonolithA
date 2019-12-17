package com.telran.ilcarro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telran.ilcarro.model.web.FilterDTO;
import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.service.model.FilterNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;

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

}
