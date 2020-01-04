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
/**
 * @author izum286
 */
public interface CarRepository extends MongoRepository<FullCarEntity, String>, CarRepositoryCustom {

 //todo
   FullCarEntity addCar(AddUpdateCarDtoRequest carToAdd);

 /**
  * @author izum286
  * @param circle
  * @param pageable
  * @return Page<FullCarEntity>
  * @status READY
  */
 Page<FullCarEntity> findAllByPickUpPlaceWithin(Circle circle, Pageable pageable);

 //todo
    FullCarEntity updateCar(AddUpdateCarDtoRequest carToUpdate);
//todo
    boolean deleteCar(UUID id);
//todo
    FullCarEntity getCarByIdForUsers(UUID id);
//todo
    List<FullCarEntity> ownerGetCars();
//todo
    FullCarEntity ownerGetCarById(UUID id);
//todo
    List<BookedPeriodDto>  ownerGetBookedPeriodsByCarId(String carId);
}
