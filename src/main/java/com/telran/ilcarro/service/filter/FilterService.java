package com.telran.ilcarro.service.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.entity.FilterNodeEntity;

public interface FilterService {
    /**
     * Method for automatically add new filter from /upload page
     * call -> addNode
     * @param addUpdateCarDtoRequest
     * @author izum286
     */
    void addFilter (AddUpdateCarDtoRequest addUpdateCarDtoRequest);

    /**
     * Method return json string of all filters
     * @return
     * @author izum286
     */
    String provideFilter() ;

    /**
     * Method added new node from FullCarDto from data which typed
     * in /upload page
     * addFilter->(called by)->addNode->(call) ->dtoToNode()+mergeNodes()
     * @param filterDTO
     * @author izum286
     */
    void addNode(FilterDTO filterDTO) ;

    /**
     * Recursive Method that takes 2 nodes to merge him in point of different values
     * node toMerge are shifted relatively existed node by 1 node down.
     * @param exist
     * @param toMerge
     * @author izum286
     */
    void mergeNodes(FilterNodeEntity exist, FilterNodeEntity toMerge);

    /**
     * Method to find index of correspondent node in list of childs
     * @param where
     * @param from
     * @return
     * @author izum286
     */
    int findNextIndx(FilterNodeEntity where, FilterNodeEntity from);
}
