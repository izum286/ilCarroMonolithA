package com.telran.ilcarro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

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
     * @return
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
     * @return
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

}
