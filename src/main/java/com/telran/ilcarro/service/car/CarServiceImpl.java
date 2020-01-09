package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.OwnerEntity;
import com.telran.ilcarro.repository.entity.UserEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Aleks Gor
 * @author izum286
 * @author Vitalii Adler
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    UserEntityRepository userRepository;


    /**
     * status - ready
     * code cleanup by izum286
     * @return optional of added car
     */
    @Override
    public Optional<FullCarDTOResponse> addCar(AddUpdateCarDtoRequest carToAdd, String userEmail) {
        try {
            FullCarEntity entity = CarMapperAddCar.INSTANCE.map(carToAdd);
            UserEntity user = userRepository.findById(userEmail).get();
            OwnerEntity owner = OwnerMapper.INSTANCE.map(user);
            entity.setOwner(owner);
            FullCarEntity added = carRepository.save(entity);
            return Optional.of(CarMapper.INSTANCE.map(added));
        } catch (ConflictRepositoryException e) {
            throw new ConflictRepositoryException(e.getMessage(), e.getCause());
        }catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     * @return optional of updated car
     */
    @Override
    public Optional<FullCarDTOResponse> updateCar(AddUpdateCarDtoRequest carToUpdate, String userEmail) {
        try {
            if(!userRepository.findById(userEmail).get().getOwnCars()
                    .contains(carToUpdate.getSerialNumber())){
                throw new RepositoryException("no such car owned by user");
            }
            Optional<FullCarEntity> entity = carRepository.findById(carToUpdate.getSerialNumber());
            if(!entity.isEmpty()){
                FullCarEntity toUpdate = CarMapper.INSTANCE.map(carToUpdate);
                carRepository.save(toUpdate);
                return Optional.of(CarMapper.INSTANCE.map(toUpdate));
            }else {
                throw new RepositoryException("something went wrong");
            }
        } catch (Throwable ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     * @return true\false
     */
    @Override
    public boolean deleteCar(String carId, String userEmail) {
        try {
            if(!userRepository.findById(userEmail).get().getOwnCars()
                    .contains(carId)){
                throw new RepositoryException("no such car owned by user");
            }
            Optional<FullCarEntity> entity = carRepository.findById(carId);
            if(!entity.isEmpty()){
                carRepository.deleteById(carId);
                return true;
            }
            return false;
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     * @return true\false
     */
    @Override
    public Optional<FullCarDTOResponse> getCarByIdForUsers(String carId) {
        try {
            Optional<FullCarEntity> entity = carRepository.findById(carId);
            List<BookedPeriodDto> shortPeriods = entity.get().getBookedPeriods()
                    .stream().map(bp->BookedPeriodMapper.INSTANCE.mapForGetCarByIdForUsers(bp))
                    .collect(Collectors.toList());
            FullCarDTOResponse toProvide = CarMapper.INSTANCE.map(entity.get());
            toProvide.setBookedPeriodDto(shortPeriods);
            return Optional.of(toProvide);
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Optional<FullCarDTOResponse> getCarByIdForOwner(String carId, String userEmail) {
        try {
            if(!userRepository.findById(userEmail).get().getOwnCars()
                    .contains(carId)){
                throw new RepositoryException("no such car owned by user");
            }
            Optional<FullCarEntity> entity = carRepository.findById(carId);
            FullCarDTOResponse response = CarMapper.INSTANCE.mapWithoutOwnerFullBookedPeriods(entity.get());
            return Optional.of(response);
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    //TODO need principal
    @Override
    public List<FullCarDTOResponse> ownerGetCars() {
        try {
            List<FullCarEntity> fullCarEntityList = carRepository.ownerGetCars();
            return fullCarEntityList.stream()
                    .map(car -> CarMapper.INSTANCE.map(car))
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }


    /**
     * status - ready
     * code cleanup by izum286
     * @return true\false
     */
    @Override
    public List<BookedPeriodDto> getBookedPeriodsByCarId(String carId) {
        try {
            List<BookedPeriodEntity> list = carRepository.findById(carId).get()
                    .getBookedPeriods();
            return list.stream().map(b-> BookedPeriodMapper.INSTANCE.map(b))
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Optional<BookResponseDTO> makeReservation(String carId, BookRequestDTO dto, String userEmail) {
        try {
            FullCarEntity entity = carRepository.getCarByIdForUsers(UUID.fromString(carId));
            List<BookedPeriodEntity> listBookedPeriodEntity = entity.getBookedPeriods() == null ? new ArrayList<>() : entity.getBookedPeriods();
            BookedPeriodEntity bookedPeriodEntity = new BookedPeriodEntity();
            bookedPeriodEntity.setBookingDate(dto.getStartDateTime());
            bookedPeriodEntity.setEndDateTime(dto.getEndDateTime());
            bookedPeriodEntity.setPersonWhoBookedDto(dto.getPersonWhoBookedDto());
            listBookedPeriodEntity.add(bookedPeriodEntity);
            entity.setBookedPeriods(listBookedPeriodEntity);

            carRepository.save(entity);

            BookResponseDTO bookResponseDTO = new BookResponseDTO();
            bookResponseDTO.setBookingDate(dto.getStartDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm")));
            bookedPeriodEntity.setPersonWhoBookedDto(dto.getPersonWhoBookedDto());

            return Optional.of(bookResponseDTO);
        } catch (ServiceException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     * @return list of three most popular cars
     */
    @Override
    public List<FullCarDTOResponse> getThreeBestCars() {
        try {
            List<FullCarEntity> fullCarEntityList = carRepository.findAll().stream()
                    .sorted(Comparator.comparing(FullCarEntity::getTrips).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            return fullCarEntityList.stream().map(car -> CarMapper.INSTANCE.map(car))
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }


    }


    @Override
    public Optional<OwnerDtoResponse> getOwnerByCarId(String carId) {
//        try {
//            OwnerEntity entity = carRepository.getOwnerByCarId(carId);
//            return Optional.of(mapperService.map(entity));
//        } catch (ServiceException ex) {
//            throw new ServiceException(ex.getMessage(), ex.getCause());
//        } catch (RepositoryException ex){
//            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
//        }
        return Optional.empty();
    }

    @Override
    public List<CarStatDto> getCarStatById(String carId) {
//        try{
//            List<CarStatEntity> carStatEntity = carRepository.getCarStatById(carId);
//            return carStatEntity.stream()
//                    .map((e)->mapperService.map(e))
//                    .collect(Collectors.toList());
//        } catch (ServiceException ex) {
//            throw new ServiceException(ex.getMessage(), ex.getCause());
//        }catch (NotFoundRepositoryException ex){
//            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
//        }
        return null;
    }


}
