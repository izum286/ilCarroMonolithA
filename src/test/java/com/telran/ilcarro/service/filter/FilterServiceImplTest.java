package com.telran.ilcarro.service.filter;

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.FilterRepository;
import com.telran.ilcarro.repository.entity.FilterNodeEntity;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class FilterServiceImplTest {

    @Autowired
    FilterService filterService;

    @MockBean
    FilterRepository filterRepository;

    AddUpdateCarDtoRequest addUpdateCarDtoRequest;
    FilterDTO filterDTO;
    FilterNodeEntity filterNodeEntity;


    @Test
    void addFilter() {
    }

    @Test
    void provideFilter() {
    }

    @Test
    void addNode() {
    }

    @Test
    void mergeNodes() {
    }

    @Test
    void findNextIndx() {
    }

    @Test
    void deleteFilters() {
    }

    @Before
    void init(){

    }
}