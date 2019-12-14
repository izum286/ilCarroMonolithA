package com.telran.ilcarro.service;


public interface MapperService {
    <U> U map(String from, Class to);

    <T> String map(T from);

    //TODO mapper entities to dto


}
