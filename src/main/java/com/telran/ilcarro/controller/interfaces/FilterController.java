package com.telran.ilcarro.controller.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * FilterController interface
 * @author izum286
 *
 */
public interface FilterController {
    String getFilters() throws JsonProcessingException;
}
