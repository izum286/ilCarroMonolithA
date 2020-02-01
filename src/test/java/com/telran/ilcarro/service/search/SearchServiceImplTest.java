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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SearchServiceImplTest {

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

    //******************************************************************************************************************

    @Test
    void cityDatesPriceSortByPrice() {
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        SearchResponse check = assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void cityDatesPriceSortByPriceWithAllArgsNull(){
        assertThrows(ServiceException.class,()->searchService.searchAllSortByPrice(0,0,null,null,null,null,null,null,0,0,true));
    }

    @Test
    void cityDatesPriceSortByPriceWithNullItemsOnPage(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,0,1));
    }


    @Test
    void cityDatesPriceSortByPriceIfCurrPageIsNull() {
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,1,0));
    }

    @Test
    void cityDatesPriceSortByPriceWithEmptyLong(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "42.542335","",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithEmptyLat(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "","42.542335",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithEmptyLongAndLat(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "","",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithRadiusNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "35.54245","34.634534",-1000,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithRadiusMoreThanMaxDouble(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "35.54245","34.634534",Double.MAX_VALUE+1,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }
    @Test
    void cityDatesPriceSortByPriceWithRadiusLessThanMinDouble(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "35.54245","34.634534",Double.MIN_VALUE-1,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithNullStartDate(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "35.54245","34.634534",1000,
               null,LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithNullEndDate(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "35.54245","34.634534",1000,
                LocalDateTime.now().plusHours(1),null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithNullAddDates(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "35.54245","34.634534",1000,
                null,null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithMinPriceWithNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                -100,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithMaxPriceWithNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                500,-1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithMaxAndMinPriceWithNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                -1500,-1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfMinPriceMoreThanMaxPrice(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                1000,50,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfStartDateMoreThanEndData(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().plusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLatitudeIsNotValidData(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "vasyapupkin1234@mail.com","23.431234",100,
                LocalDateTime.now().plusHours(1),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLongitudeIsNotValidData(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "23.431234","vasyapupkin1234@mail.com",100,
                LocalDateTime.now().plusHours(1),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLatitudeAndLongitudeIsNotValidData(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                NullPointerException.class.getName(),"vasyapupkin1234@mail.com",100,
                LocalDateTime.now().plusHours(1),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfCarsNotFound(){
        init();
        List<FullCarEntity> tmp = new ArrayList<>();
        doReturn(new PageImpl<>(tmp)).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(NotFoundServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,"23.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber2IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333",null,100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1_2IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,null,100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1_2_4IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,null,100,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1_2_5IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,null,100,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1_4_5IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,"23.431234",100,
                null,null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber4_5IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                null,null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber5IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber4IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","23.431234",100,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1_4IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,"23.431234",100,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithArgsNumber1_5IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                null,"23.431234",100,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false,3,1));
    }
    @Test
    void cityDatesPriceSortByPriceWithArgsNumber2_4IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333",null,100,
                null,LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
    }
    @Test
    void cityDatesPriceSortByPriceWithArgsNumber2_5IsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333",null,100,
                LocalDateTime.now().minusDays(2),null,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithItemsOnPageWithNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(2),
                50,1000,false,-1,1));
    }

    //TODO message is "errPage size must not be less than one" but we check More maxInteger
    @Test
    void cityDatesPriceSortByPriceWithItemsOnPageWithMoreThanMaxIntegerValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(2),
                50,1000,false,Integer.MAX_VALUE+1,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithItemsOnPageWithLessThanMinIntegerValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(2),
                50,1000,false,Integer.MIN_VALUE-1,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithCurrPageWithMoreThanMaxIntegerValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(2),
                50,1000,false,1,Integer.MAX_VALUE+1));
    }

    @Test
    void cityDatesPriceSortByPriceWithCurrPageWithLessThanMinIntegerValue(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.now().minusDays(1),LocalDateTime.now().plusDays(2),
                50,1000,false,1,Integer.MIN_VALUE-1));
    }

    @Test
    void cityDatesPriceSortByPriceWithMaxStartAndEndDates(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.MAX,LocalDateTime.MAX.plusDays(1),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithMinStartAndEndDates(){
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                LocalDateTime.MIN,LocalDateTime.MIN.minusDays(1),
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithIdenticallyDate(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceWithIdenticallyDateAndPrice(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "14.43333","14.43333",100,
                now,now,
                50,50,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLatitudeHaveNegativeValue(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "-14.43333","14.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLongitudeHaveNegativeValue(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "14.43333","-14.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfAltitudeAndLongitudeHaveNegativeValue(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "-14.43333","-34.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfAltitudeMoreThan90(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "89.43333","-34.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfAltitudeLessThanNegative90(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "-95.43333","-34.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLatitudeMoreThan180(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "89.43333","185.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    @Test
    void cityDatesPriceSortByPriceIfLatitudeLessThanNegative180(){
        init();
        LocalDateTime now = LocalDateTime.now();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        assertThrows(ServiceException.class,()->searchService.cityDatesPriceSortByPrice(
                "89.43333","-185.43333",100,
                now,now,
                50,1000,false,3,1));
    }

    //*********************************************************************************************************************************
    //*********************************************************************************************************************************

    @Test
    void geoAndRadius() {
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        SearchResponse check = assertDoesNotThrow(()->searchService.geoAndRadius("35.235235","45.562345235","1000",3,1));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void geoAndRadiusWithAllArgsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius(null,null,null,0,0));
    }

    @Test
    void geoAndRadiusWithLatArgIsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius(null,"45.562345235","1000",3,1));

    }

    @Test
    void geoAndRadiusWithLongArgIsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235",null,"1000",3,1));

    }

    @Test
    void geoAndRadiusWithLatAndLongArgsIsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius(null,null,"1000",3,1));

    }

    @Test
    void geoAndRadiusWithLatAndRadiusArgsIsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius(null,"45.562345235",null,3,1));

    }

    @Test
    void geoAndRadiusWithLongAndRadiusArgsIsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235",null,null,3,1));

    }

    @Test
    void geoAndRadiusWithRadiusIsNull(){
        init();
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235",null,3,1));

    }

    @Test
    void geoAndRadiusIfRadiusMoreThanMaxValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        Double tmp = Double.MAX_VALUE+1;
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235",tmp.toString(),3,1));

    }

    @Test
    void geoAndRadiusIfRadiusLessThanMinValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        Double tmp = Double.MIN_VALUE-1;
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235",tmp.toString(),3,1));

    }

    @Test
    void geoAndRadiusIfLatitudeMoreThan90(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("135.235235","45.562345235","1000",3,1));
    }

    @Test
    void geoAndRadiusIfLatitudeLessThanNegative90(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("-835.235235","45.562345235","1000",3,1));

    }

    @Test
    void geoAndRadiusIfLongitudeMoreThan180(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","545.562345235","1000",3,1));

    }

    @Test
    void geoAndRadiusIfLongitudeLessThanNegative180(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","-545.562345235","1000",3,1));

    }

    @Test
    void geoAndRadiusIfLatitudeAndLongitudeIsNegativeValues(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertDoesNotThrow(()->searchService.geoAndRadius("-35.235235","-45.562345235","1000",3,1));
    }

    @Test
    void geoAndRadiusIfItemsOnPageIsNegative(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",-3,1));
    }

    @Test
    void geoAndRadiusIfItemsOnPageIsMoreThanMaxValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",Integer.MAX_VALUE+1,1));

    }

    @Test
    void geoAndRadiusIfItemsOnPageIsLessThanMinValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",Integer.MIN_VALUE-1,1));
    }

    @Test
    void geoAndRadiusIfCurrPageIsNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",3,-1));

    }

    @Test
    void geoAndRadiusIfCurrPageMoreThanMaxValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",3,Integer.MAX_VALUE+1));
    }

    @Test
    void geoAndRadiusIfCurrPageLessThanMinValue(){
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(ServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",3,Integer.MIN_VALUE-1));

    }

    @Test
    void geoAndRadiusIfCarsNotFound(){
        List<FullCarEntity> tmp = new ArrayList<>();
        doReturn(new PageImpl<>(tmp)).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        assertThrows(NotFoundServiceException.class,()->searchService.geoAndRadius("35.235235","45.562345235","1000",3,1));
    }

    //*********************************************************************************************************************************
    //*********************************************************************************************************************************

    @Test
    void byFilter() {
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        SearchResponse check = assertDoesNotThrow(()->searchService.byFilter(filterDTO,3,1));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void byFilterWithAllFieldsOfDtoIsNulls(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        FilterDTO tmp = new FilterDTO();
        assertThrows(ServiceException.class,()->searchService.byFilter(tmp,3,1));
    }

    @Test
    void byFilterIfItemsOnPageIsNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,-5,1));
    }

    @Test
    void byFilterIfItemsOnPageMoreThanMaxInteger(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,Integer.MAX_VALUE+1,1));
    }

    @Test
    void byFilterIfItemsOnPageLesThenMinInteger(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,Integer.MIN_VALUE-1,1));
    }

    @Test
    void byFilterIfCurrPageIsNegativeValue(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,3,-5));
    }

    @Test
    void byFilterIfCurrPageMoreThanMaxInteger(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,3,Integer.MAX_VALUE+1));
    }

    @Test
    void byFilterIfCurrPageLessThanMinInteger(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,3,Integer.MIN_VALUE-1));
    }

    @Test
    void byFilterIfCurrPageIsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,3,0));
    }

    @Test
    void byFilterIfItemsOnPageIsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(filterDTO,0,1));
    }

    @Test
    void byFilterIfDtoIsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(null,3,1));
    }

    @Test
    void byFilterIfAllArgsIsNull(){
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        assertThrows(ServiceException.class,()->searchService.byFilter(null,0,0));
    }

    @Test
    void byFilterIfCarsNotFound(){
        List<FullCarEntity> tmp = new ArrayList<>();
        doReturn(new PageImpl<>(tmp)).when(carRepository).byFilter(any(),any());
        assertThrows(NotFoundServiceException.class,()->searchService.byFilter(filterDTO,3,1));
    }

    //*********************************************************************************************************************************
    //*********************************************************************************************************************************

    @Test
    void searchAllSortByPrice() {
        init();
        doReturn(pageForResponse).when(carRepository).searchAllSortByPrice(anyInt(),anyInt(),any(),anyString(),
                any(),anyString(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        SearchResponse check = assertDoesNotThrow(()->searchService.searchAllSortByPrice(3,1,filterDTO,"45235.53235","43454.345345","1000",LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    //*********************************************************************************************************************************

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