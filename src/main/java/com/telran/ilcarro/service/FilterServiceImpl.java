package com.telran.ilcarro.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.telran.ilcarro.model.web.FilterDTO;
import com.telran.ilcarro.model.web.FullCarDTO;
import com.telran.ilcarro.repository.FilterRepository;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.model.FilterNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {
    @Autowired
    MapperService mapperService;

    @Autowired
    FilterRepository filterRepository;

    /**
     * Method for automatically add new filter from /upload page
     * call -> addNode
     *
     * @param fullCarDTO
     * @author izum286
     */
    @Override
    public void addFilter(FullCarDTO fullCarDTO) throws IllegalAccessException {
        addNode(mapperService.map(fullCarDTO));
    }

    /**
     * Method return json string of all filters
     *
     * @return
     * @author izum286
     */
    @Override
    public String provideFilter() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("custom",
                Version.unknownVersion());
        module.addSerializer(FilterNode.class, new FilterNodeSerializer());
        mapper.registerModule(module);
        FilterNode root = new FilterNode();
        root.setValue("allCars");
        if(filterRepository.size() == 0){
            throw new NotFoundServiceException("There is no filters yet");
        }
        for (FilterNode n: filterRepository.values()){
            root.getChilds().add(n);
        }
        String s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        return s;
    }

    /**
     * Method added new node from FullCarDto from data which typed
     * in /upload page
     * addFilter->(called by)->addNode->(call) ->dtoToNode()+mergeNodes()
     *
     * @param filterDTO
     * @author izum286
     */
    @Override
    public void addNode(FilterDTO filterDTO) throws IllegalAccessException {
        FilterNode toRawAdd = mapperService.map(filterDTO);
        if(!filterRepository.containsKey(filterDTO.getMake())){
            filterRepository.put(filterDTO.getMake(), toRawAdd);
        }else {
            //начинаем сравнивать ноды со сдвигом на 1 в ноде добавления СВЕРХУ
            //и в случае разницы  - сливаем
            FilterNode fromList = filterRepository.get(filterDTO.getMake());
            FilterNode toCompare = toRawAdd.getChilds().get(0);
            mergeNodes(fromList, toCompare);
        }
    }

    /**
     * Recursive Method that takes 2 nodes to merge him in point of different values
     * node toMerge are shifted relatively existed node by 1 node down.
     *
     * @param exist
     * @param toMerge
     * @author izum286
     */
    @Override
    public void mergeNodes(FilterNode exist, FilterNode toMerge) {
        // avoiding adding duplicates in last node
        if (toMerge.getType().equals("exit")){
            return;
        }
        if(exist.getChilds().stream().
                anyMatch(n->n.getValue().equals(toMerge.getValue()))){
            int indx = findNextIndx(exist, toMerge);
            /**
             * alternative way to find index
             * AtomicInteger i = new AtomicInteger(); // any mutable integer wrapper
             * int index = fromList.getChilds().stream()
             *     .peek(v -> i.incrementAndGet())
             *     .anyMatch(node -> node.getValue().equals(toCompare.getValue())) ? i.get()-1 : -1;
             * @author: Inozemtsev
             */
            mergeNodes(exist.getChilds().get(indx), toMerge.getChilds().stream().findFirst().orElse(new FilterNode("exit")));
        }else {
            exist.getChilds().add(toMerge);
        }
    }

    /**
     * Method to find index of correspondent node in list of childs
     *
     * @param exist
     * @param toMerge
     * @return
     * @author izum286
     */
    @Override
    public int findNextIndx(FilterNode exist, FilterNode toMerge) {
        int count =0;
        List<FilterNode> list = exist.getChilds();
        for (int i = 0; i < list.size(); i++) {
            if(!list.get(i).getValue().equals(toMerge.getValue())){
                count+=1;
            }
        }
        return count;
    }
}
