package com.telran.ilcarro.service.search;

import com.telran.ilcarro.model.car.FeatureDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.PickUpPlaceDto;
import com.telran.ilcarro.model.car.SearchResponse;
import com.telran.ilcarro.model.filter.FilterDTO;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.CarRepositoryCustom;
import com.telran.ilcarro.repository.FilterRepository;
import com.telran.ilcarro.repository.entity.*;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.FeatureMapper;
import com.telran.ilcarro.service.mapper.OwnerMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceSearchAllSortByPriceTests {

    @Test
    void searchAllSortByPrice() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        SearchResponse check = assertDoesNotThrow(()->searchService.searchAllSortByPrice(3,1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void searchAllSortByPriceIfItemsOnPageIsNegative() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(-3,1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfItemsOnPageMoreThanMaxInteger() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(Integer.MAX_VALUE+1,1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfItemsOnPageLessThanMinInteger() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(Integer.MIN_VALUE-1,1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfCurrPageIsNegativeValue() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,-21,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfCurrPageMoreThanMaxInteger() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,Integer.MAX_VALUE+1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfCurrPageLessTheMinInteger() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,Integer.MIN_VALUE-1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfDtoIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,null,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfDtoHaveOnlyNullFields() {
        init();
        FilterDTO tmp = new FilterDTO();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,tmp,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLatitudeIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,"54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLongitudeIsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,"1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLatitudeAndLongitudeIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,"1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLatitudeHaveNotValidValue() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO, CarRepositoryCustom.class.getName(),"54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLongitudeHaveNotValidValue() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235", NotFoundServiceException.class.getName(),"1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLatitudeMoreThan90() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"135.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLatitudeLessThenNegative90() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"-535.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLongitudeMoreThan180() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","454.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfLongitudeLessThen180() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","-754.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfAltitudeNadLongitudeIsNegativeValidValues() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"-35.53235","-54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfRadiusIsNegativeValue() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","-1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfRadiusIsMoreMaxValue() {
        init();
        Double tmp = Double.MAX_VALUE+1;
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345",tmp.toString(),
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfRadiusIsLessMinValue() {
        init();
        Double tmp = Double.MIN_VALUE-1;
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345",tmp.toString(),
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfRadiusIsNotValidValue() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","vasyaPupkinPeredaetPrivet",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfStartDateMoreThanEndDate() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().plusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfStartDateValueAndEndDateValueIsMaxValueWithDifferenceDay() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.MAX,LocalDateTime.MAX.plusDays(1),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfStartDateValueAndEndDateValueIsMinValueWithDifferenceDay() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.MIN.minusDays(1),LocalDateTime.MIN,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfStartDateIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfEndDateIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));

    }

    @Test
    void searchAllSortByPriceIfDateIsIdenticallyDates() {
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                now,now,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfMinPriceIsNegative() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                -50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfMinPriceIsMaxValue() {
        init();
        Double tmp = Double.MAX_VALUE;
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),tmp,1000,false));
    }

    @Test
    void searchAllSortByPriceIfMinPriceIsMinValue() {
        init();
        Double tmp = Double.MIN_VALUE;
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                tmp,1000,false));
    }

    @Test
    void searchAllSortByPriceIfMinPriceIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                0,1000,false));
    }

    @Test
    void searchAllSortByPriceIfMaxPriceIsNegativeValue() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,-1000,false));
    }

    @Test
    void searchAllSortByPriceIfMaxPriceMoreThanMaxValue() {
        init();
        Double tmp = Double.MAX_VALUE+1;
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,tmp,false));
    }

    @Test
    void searchAllSortByPriceIfMaxPriceLessThanMinValue() {
        init();
        Double tmp = Double.MIN_VALUE-1;
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,tmp,false));
    }

    @Test
    void searchAllSortByPriceIfMaxPriceLessThanMinPrice() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,10,false));
    }

    @Test
    void searchAllSortByPriceIfMaxPriceAndMinPriceIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                0,0,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_3_4_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,"54.345345","1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_3_5_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235",null,"1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_3_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235","54.345345",null,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235","54.345345","1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235","54.345345","1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_5_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,"1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,"54.345345",null,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,"54.345345","1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,"54.345345","1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_5_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,"1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_5_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,"1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_5_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,"1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345",null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345",null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_7_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345","1000",
                null,null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,"1000",
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,"54.345345",null,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,"54.345345","1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,"54.345345","1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_5_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235",null,null,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_5_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235",null,"1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_5_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235",null,"1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235","54.345345",null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235","54.345345",null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_5_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,null,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_5_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,"1000",
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_5_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,"1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,"54.345345",null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,"54.345345",null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_5_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_5_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_6_7_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235","54.345345",null,
                null,null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_6_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,null,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,"1000",
                null,LocalDateTime.now().minusDays(2),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,"1000",
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_5_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235",null,null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_5_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235",null,null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_3_6_7_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,"35.53235","54.345345",null,
                null,null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_5_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_4_5_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }
    @Test
    void searchAllSortByPriceIfArgsNum_5_6_7_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,"35.53235",null,null,
                null,null,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_6_7_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,null,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_6_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,null,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_4_5_6_7_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                filterDTO,null,null,null,
                null,null,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfArgsNum_3_4_5_6_7_8_isNull() {
        init();
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(3,1,
                null,null,null,null,
                null,null,
                50,1000,false));
    }

    @Test
    void searchAllSortByPriceIfCarsNotFound(){
        List<FullCarEntity> tmp = new ArrayList<>();
        doReturn(new PageImpl<>(tmp)).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(NotFoundServiceException.class,()->searchService.searchAllSortByPrice(3,1,filterDTO,"35.53235","54.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
    }

    @Autowired
    SearchService searchService;

    @MockBean
    CarRepository carRepository;

    @MockBean
    FilterRepository filterRepository;

    private FullCarEntity fullCarEntity1;
    private FullCarEntity fullCarEntity2;
    private FullCarEntity fullCarEntity3;
    private UserEntity userEntity;
    private Page<FullCarEntity> pageForResponse;
    private FilterDTO filterDTO;


    @Before
    public void init(){

        filterDTO = FilterDTO.builder()
                .engine("2l")
                .fuel("kerosine")
                .fuel_consumption("1L/100Km")
                .gear("manual")
                .horsepower("96")
                .make("mazda")
                .model("3")
                .wheels_drive("FWD")
                .year("2010")
                .build();

        List<FeatureDto> featureDtos = List.of(
                FeatureDto.builder().feature("MAGNITOLA!!!").build(),
                FeatureDto.builder().feature("JIGURDA!").build());

        List<FeatureEntity> featureEntityList = featureDtos.stream()
                .map(FeatureMapper.INSTANCE::map)
                .collect(Collectors.toList());

        PickUpPlaceDto pickUpPlaceDto = PickUpPlaceDto.builder()
                .place_id(UUID.randomUUID().toString())
                .longitude(42312.4443412344f)
                .latitude(34512.5136246236f)
                .build();

        List<BookedPeriodEntity> bookedPeriodEntities = new ArrayList<>();

        List<String> carSerialLiesList = new ArrayList<>();
        List<BookedPeriodEntity> bookedPeriodLieList = new ArrayList<>();
        bookedPeriodLieList.add(BookedPeriodEntity.builder()
                .paid(true)
                .active(true)
                .amount(23423)
                .bookingDate(LocalDateTime.now().minusDays(3))
                .endDateTime(LocalDateTime.now().plusHours(23))
                .orderId("12345")
                .personWhoBooked(PersonWhoBooked.builder()
                        .email("vasyapupkin1234@mail.com")
                        .first_name("Vasya")
                        .phone("1234567899")
                        .second_name("Pupkin")
                        .build())
                .startDateTime(LocalDateTime.now().minusDays(2))
                .carId("32-222-23")
                .build());

        userEntity = UserEntity.builder()
                .email("vasyapupkin1234@mail.com")
                .comments(emptyList())
                .firstName("Vasya")
                .history(null)
                .lastName("Pupkin")
                .photo("https://someurl.com/image.jpeg")
                .registrationDate(LocalDateTime.now().minusDays(24))
                .driverLicense("5r1325136135")
                .isDeleted(false)
                .location(LocationEntity.builder()
                        .zip(12124)
                        .street("strit")
                        .state("staaate")
                        .lon("4323.564345")
                        .lat("5345.323423")
                        .isVehicle(false)
                        .country("cauntri")
                        .city("ceeety")
                        .build())
                .ownCars(carSerialLiesList)
                .phone("452346236235")
                .bookedCars(bookedPeriodLieList)
                .build();

        bookedPeriodEntities.add(BookedPeriodEntity.builder()
                .paid(true)
                .bookingDate(LocalDateTime.now().minusDays(2))
                .amount(31244f)
                .endDateTime(LocalDateTime.now().plusHours(23))
                .orderId("12345")
                .personWhoBooked(PersonWhoBooked.builder()
                        .second_name("Pupkin")
                        .phone("1234567899")
                        .first_name("Vasya")
                        .email("vasyapupkin1234@mail.com")
                        .build())
                .startDateTime(LocalDateTime.now().minusDays(2))
                .carId("32-222-23")
                .active(true)
                .build());

        OwnerEntity ownerEntity = OwnerMapper.INSTANCE.map(userEntity);


        CarStatEntity carStatEntity = CarStatEntity.builder().rating(45.233f).trips(1).build();

        PickUpPlaceEntity pickUpPlaceEntity = PickUpPlaceEntity.builder()
                .latitude(pickUpPlaceDto.getLatitude())
                .longitude(pickUpPlaceDto.getLongitude())
                .place_id(pickUpPlaceDto.getPlace_id())
                .build();

        String about = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                "software like Aldus PageMaker including versions of Lorem Ipsum.";

        List<String> imageList = new ArrayList<>();
        imageList.add("https://someurlwithsomeimage.com/image.jpg");


        fullCarEntity1 = FullCarEntity.builder()
                .year("2010")
                .wheelsDrive("FWD")
                .trips(1)
                .torque(87)
                .serialNumber("22-627-43")
                .seats(5)
                .model("6")
                .horsePower(96)
                .gear("manual")
                .fuelConsumption(104.4f)
                .fuel("kerosine")
                .engine("2L")
                .doors(5)
                .distanceIncluded(100)
                .carClass("Active")
                .about(about)
                .features(featureEntityList)
                .bookedPeriods(bookedPeriodEntities)
                .imageUrl(imageList)
                .make("Mazda")
                .pricePerDay(PricePerDayEntity.builder().currency("usd").value(100).build())
                .statistics(carStatEntity)
                .pickUpPlace(new double[]{pickUpPlaceEntity.getLatitude(), pickUpPlaceEntity.getLongitude()})
                .isDeleted(false)
                .owner(ownerEntity)
                .pricePerDaySimple(100)
                .build();

        fullCarEntity2 = FullCarEntity.builder()
                .year("2010")
                .wheelsDrive("FWD")
                .trips(1)
                .torque(87)
                .serialNumber("22-172-53")
                .seats(5)
                .model("3")
                .horsePower(96)
                .gear("automat")
                .fuelConsumption(104.4f)
                .fuel("diesel")
                .engine("1.6L")
                .doors(5)
                .distanceIncluded(100)
                .carClass("Compact")
                .about(about)
                .features(featureEntityList)
                .bookedPeriods(bookedPeriodEntities)
                .imageUrl(imageList)
                .make("Mazda")
                .pricePerDay(PricePerDayEntity.builder().currency("usd").value(200).build())
                .statistics(carStatEntity)
                .pickUpPlace(new double[]{pickUpPlaceEntity.getLatitude(), pickUpPlaceEntity.getLongitude()})
                .isDeleted(false)
                .owner(ownerEntity)
                .pricePerDaySimple(200)
                .build();

        fullCarEntity3 = FullCarEntity.builder()
                .year("2010")
                .wheelsDrive("FWD")
                .trips(1)
                .torque(87)
                .serialNumber("42-252-22")
                .seats(5)
                .model("Civic")
                .horsePower(96)
                .gear("robotic")
                .fuelConsumption(104.4f)
                .fuel("benzin")
                .engine("1.8L")
                .doors(5)
                .distanceIncluded(100)
                .carClass("Active")
                .about(about)
                .features(featureEntityList)
                .bookedPeriods(bookedPeriodEntities)
                .imageUrl(imageList)
                .make("Honda")
                .pricePerDay(PricePerDayEntity.builder().currency("usd").value(100).build())
                .statistics(carStatEntity)
                .pickUpPlace(new double[]{pickUpPlaceEntity.getLatitude(), pickUpPlaceEntity.getLongitude()})
                .isDeleted(false)
                .owner(ownerEntity)
                .pricePerDaySimple(100)
                .build();

        List<FullCarEntity> list = new ArrayList<>();
        list.add(fullCarEntity1);
        list.add(fullCarEntity2);
        list.add(fullCarEntity3);

        PageRequest pageRequest = PageRequest.of(1,3);

        pageForResponse = new PageImpl<>(list,pageRequest,3);

    }
}
