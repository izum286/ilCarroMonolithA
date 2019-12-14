package com.telran.ilcarro.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FilterRepo {

    private void addFilter(String mockFilter){

    }

    private Map<Object, Object> getFilter(){
        return new ConcurrentHashMap<>();
    }
}
