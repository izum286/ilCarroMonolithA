package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.car.AddUpdateCarDtoRequest;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;
//todo mock repo
public interface CarRepository extends MongoRepository<FullCarEntity, String> {

   FullCarEntity addCar(AddUpdateCarDtoRequest carToAdd);



    Page<FullCarEntity> findAllByPickUpPlaceWithin(Circle circle, Pageable pageable);


    FullCarEntity updateCar(AddUpdateCarDtoRequest carToUpdate);

    boolean deleteCar(UUID id);

    FullCarEntity getCarByIdForUsers(UUID id);

    List<FullCarEntity> ownerGetCars();

    FullCarEntity ownerGetCarById(UUID id);

    List<BookedPeriodDto>  ownerGetBookedPeriodsByCarId(String carId);
}
