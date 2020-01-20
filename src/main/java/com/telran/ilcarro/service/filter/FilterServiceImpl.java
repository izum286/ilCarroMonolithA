package com.telran.ilcarro.service.filter;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.FilterRepository;
import com.telran.ilcarro.repository.entity.FilterNodeEntity;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;


@Service
public class FilterServiceImpl implements FilterService {

    @Autowired
    FilterRepository filterRepository;

    /**
     * Method for automatically add new filter from /upload page
     * call -> addNode
     *
     *
     * @author izum286
     */
    @Override
    public void addFilter(AddUpdateCarDtoRequest addUpdateCarDtoRequest) {
        addNode(map(addUpdateCarDtoRequest));
    }

    /**
     * Method return json string of all filters
     *
     * @return String
     * @author izum286
     */
    @Override
    public String provideFilter(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule("custom",
                    Version.unknownVersion());
            module.addSerializer(FilterNodeEntity.class, new FilterNodeSerializer());
            mapper.registerModule(module);
            FilterNodeEntity res = filterRepository.findById("root").orElseGet(()->new FilterNodeEntity("root", "allCars"));
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
        } catch (Throwable e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Method added new node from FullCarDto from data which typed
     * in /upload page
     * addFilter->(called by)->addNode->(call) ->dtoToNode()+mergeNodes()
     *
     * @param filterDTO filter
     * @author izum286
     */
    @Override
    public void addNode(FilterDTO filterDTO)  {
        try {
            FilterNodeEntity toRawAdd = map(filterDTO);
            if(filterRepository.findById("root").isEmpty()){
                FilterNodeEntity root = new FilterNodeEntity("root", "allCars");
                root.getChilds().add(toRawAdd);
                filterRepository.save(root);
            }else {
                FilterNodeEntity root = filterRepository.findById("root").orElseThrow(()->new RuntimeException("something went wrong with DB"));
                List<FilterNodeEntity> childs = root.getChilds();

                Optional<FilterNodeEntity> fromDb
                        = childs.stream().filter(child->child.getValue().equals(filterDTO.getMake())).findAny();

                if(!fromDb.isPresent()){
                    root.getChilds().add(toRawAdd);
                    filterRepository.save(root);
                }else{
                    //начинаем сравнивать ноды со сдвигом на 1 в ноде добавления СВЕРХУ
                    //и в случае разницы  - сливаем
                    mergeNodes(fromDb.get(), toRawAdd.getChilds().get(0));
                    filterRepository.save(root);
                }
            }
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
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
    @SuppressWarnings("JavaDoc")
    @Override
    public void mergeNodes(FilterNodeEntity exist, FilterNodeEntity toMerge) {
        try {
            // avoiding adding duplicates in last node
            if (toMerge.getType().equals("exit")){
                return;
            }
            if(exist.getChilds().stream().
                    anyMatch(n->n.getValue().equals(toMerge.getValue()))){
                int indx = findNextIndx(exist, toMerge);
                /*
                 * alternative way to find index
                 * AtomicInteger i = new AtomicInteger(); // any mutable integer wrapper
                 * int index = fromList.getChilds().stream()
                 *     .peek(v -> i.incrementAndGet())
                 *     .anyMatch(node -> node.getValue().equals(toCompare.getValue())) ? i.get()-1 : -1;
                 * @author: Inozemtsev
                 */
                mergeNodes(exist.getChilds().get(indx), toMerge.getChilds().stream().findFirst().orElse(new FilterNodeEntity("exit")));
            }else {
                exist.getChilds().add(toMerge);
            }
        } catch (Throwable e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Method to find index of correspondent node in list of childs
     *
     * @param exist FilterNodeEntity
     * @param toMerge FilterNodeEntity
     * @return int
     * @author izum286
     */
    @Override
    public int findNextIndx(FilterNodeEntity exist, FilterNodeEntity toMerge) {
        int count =0;
        List<FilterNodeEntity> list = exist.getChilds();
        for (FilterNodeEntity filterNodeEntity : list) {
            if (!filterNodeEntity.getValue().equals(toMerge.getValue())) {
                count += 1;
            }
        }
        return count;
    }



    /**
     * mapping method from FilterDto to FilterNode
     * participated in invoke chain internal methods of FilterService
     *
     * @param toAdd FilterDTO
     * @return new FilterNode
     * @author izum286
     */

    public FilterNodeEntity map(FilterDTO toAdd) throws IllegalAccessException {
        List<FilterNodeEntity> nodes = new CopyOnWriteArrayList<>();
        Field[] f = toAdd.getClass().getDeclaredFields();
        for (Field field : f) {
            field.setAccessible(true);
            String fieldType = field.getName();
            String fieldValue = (String) field.get(toAdd);
            FilterNodeEntity node = new FilterNodeEntity();
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
     * mapping method from FullCarDto to FilterDto
     * participated in invoke chain of /upload->save page
     *
     * @param from AddUpdateCarDtoRequest
     * @return new FilterDto
     * @author izum286
     */
//TODO This mapper need to be described by MapStruct
    public FilterDTO map(AddUpdateCarDtoRequest from) {
        return FilterDTO.builder()
                .make(from.getMake())
                .model(from.getModel())
                .year(from.getYear())
                .engine(from.getEngine())
                .fuel(from.getFuel())
                .transmission(from.getGear())
                .wheels_drive(from.getWheelsDrive())
                .horsepower(String.valueOf(from.getHorsePower()))
                .torque(String.valueOf(from.getTorque()))
                .doors(String.valueOf(from.getDoors()))
                .seats(String.valueOf(from.getSeats()))
                .car_class(from.getCarClass())
                .fuel_consumption(String.valueOf(from.getFuelConsumption()))
                .build();
    }

}
