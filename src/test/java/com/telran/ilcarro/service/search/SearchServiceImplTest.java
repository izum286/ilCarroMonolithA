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

    @Test
    void cityDatesPriceSortByPrice() {
        init();
        doReturn(pageForResponse).when(carRepository).cityDatesPriceSortByPrice(anyString(),anyString(),
                anyDouble(),any(),any(),anyDouble(),anyDouble(),any(),anyBoolean());
        SearchResponse check = assertDoesNotThrow(()->searchService.cityDatesPriceSortByPrice(
                "2314.43333","3323.431234",100,
                LocalDateTime.now().minusDays(2),LocalDateTime.now().plusHours(3),
                50,1000,false,3,1));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void geoAndRadius() {
        init();
        doReturn(pageForResponse).when(carRepository).findAllByPickUpPlaceWithin(any(),any());
        SearchResponse check = assertDoesNotThrow(()->searchService.geoAndRadius("234235.235235","3345.562345235","1000",3,1));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void byFilter() {
        init();
        doReturn(pageForResponse).when(carRepository).byFilter(any(),any());
        SearchResponse check = assertDoesNotThrow(()->searchService.byFilter(filterDTO,3,1));
        List<FullCarDTOResponse> toCheck = check.getCars();
        assertEquals(3,toCheck.size());
    }

    @Test
    void searchAllSortByPrice() {
    }

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