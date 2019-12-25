package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.FilterDTO;
import com.telran.ilcarro.model.web.FullCarDTOResponse;
import com.telran.ilcarro.service.model.FilterNode;

public interface FilterService {
    /**
     * Method for automatically add new filter from /upload page
     * call -> addNode
     * @param fullCarDTOResponse
     * @author izum286
     */
    void addFilter (FullCarDTOResponse fullCarDTOResponse) throws IllegalAccessException;

    /**
     * Method return json string of all filters
     * @return
     * @author izum286
     */
    String provideFilter();

    /**
     * Method added new node from FullCarDto from data which typed
     * in /upload page
     * addFilter->(called by)->addNode->(call) ->dtoToNode()+mergeNodes()
     * @param filterDTO
     * @author izum286
     */
    void addNode(FilterDTO filterDTO) throws IllegalAccessException;

    /**
     * Recursive Method that takes 2 nodes to merge him in point of different values
     * node toMerge are shifted relatively existed node by 1 node down.
     * @param exist
     * @param toMerge
     * @author izum286
     */
    void mergeNodes(FilterNode exist, FilterNode toMerge);

    /**
     * Method to find index of correspondent node in list of childs
     * @param where
     * @param from
     * @return
     * @author izum286
     */
    int findNextIndx(FilterNode where, FilterNode from);
}
