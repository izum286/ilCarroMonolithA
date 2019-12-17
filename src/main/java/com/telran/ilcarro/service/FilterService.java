package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.FilterDTO;
import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.service.model.FilterNode;

public interface FilterService {
    void addFilter (FullCarDTO fullCarDTO);
    String provideFilter();

    void addNode(FilterDTO filterDTO);
    void mergeNodes(FilterNode exist, FilterNode toMerge);
}
