package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.repository.BookedPeriodsRepository;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.UserEntityRepository;
import com.telran.ilcarro.repository.entity.*;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.BookedPeriodMapper;
import com.telran.ilcarro.service.mapper.CarMapper;
import com.telran.ilcarro.service.mapper.OwnerMapper;
import com.telran.ilcarro.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    UserService userService;

    @Autowired
    BookedPeriodsRepository bookedPeriodsRepository;


    /**
     * status - ready
     * code cleanup by izum286
     *
     * @return optional of added car
     */
    @Override
    public Optional<FullCarDTOResponse> addCar(AddUpdateCarDtoRequest carToAdd, String userEmail) {
        try {
            FullCarEntity entity = CarMapper.INSTANCE.map(carToAdd);
            UserEntity user = userRepository.findById(userEmail).orElseThrow(
                    () -> new NotFoundServiceException(String.format("User %s not found", userEmail))
            );
            OwnerEntity owner = OwnerMapper.INSTANCE.map(user);
            entity.setOwner(owner);
            entity.setStatistics(new CarStatEntity(0, 0));
            //Add car serialNumber to user profile
            FullCarEntity added = carRepository.save(entity);
            userService.addUserCar(userEmail, carToAdd.getSerialNumber());
            return Optional.of(CarMapper.INSTANCE.map(added));
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     * code refactoring Anton Konkin
     *
     * @return optional of updated car
     */
    @Override
    public Optional<FullCarDTOResponse> updateCar(AddUpdateCarDtoRequest carToUpdate, String userEmail) {
        try {
            if (!userRepository.findById(userEmail)
                    .orElseThrow(() -> new NotFoundServiceException(String.format("User profile %s not found", userEmail))).getOwnCars()
                    .contains(carToUpdate.getSerialNumber())) {
                throw new RepositoryException("no such car owned by user");
            }
            FullCarEntity entity = carRepository.findById(carToUpdate.getSerialNumber()).orElseThrow(
                    () -> new NotFoundServiceException(String.format("Car with id %s not found in carRepository", carToUpdate.getSerialNumber()))
            );
            FullCarEntity toUpdate = CarMapper.INSTANCE.map(carToUpdate);
            CarMapper.INSTANCE.updCar(entity, toUpdate);
            carRepository.save(toUpdate);
            return Optional.of(CarMapper.INSTANCE.map(entity));
        } catch (Throwable ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     *
     * @return true\false
     */
    @Override
    public boolean deleteCar(String carId, String userEmail) {
        try {
            if (!userRepository.findById(userEmail).orElseThrow(
                    () -> new NotFoundServiceException(String.format("User profile %s not found", userEmail))
            ).getOwnCars()
                    .contains(carId)) {
                throw new RepositoryException("no such car owned by user");
            }

            FullCarEntity carEntity = carRepository.findById(carId).orElseThrow(
                    () -> new NotFoundServiceException(String.format("Car with id %s not found in carRepository", carId))
            );
            carEntity.setDeleted(true);
            carRepository.save(carEntity);
            return true;
        } catch (Throwable ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup and refactoring by Konkin Anton
     *
     * @return Optional<FullCarDTOResponse>
     */
    @Override
    public Optional<FullCarDTOResponse> getCarByIdForUsers(String carId) {
        try {
            FullCarEntity entity = carRepository.findById(carId).orElseThrow(
                    () -> new NotFoundServiceException(String.format("Car with id %s not found in carRepository", carId))
            );
            FullCarDTOResponse toProvide = CarMapper.INSTANCE.mapForGetCarByIdForUsers(entity);
            return Optional.of(toProvide);
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * status - ready
     *
     * @return Optional<FullCarDTOResponse>
     * @author - izum286
     */
    @Override
    public Optional<FullCarDTOResponse> getCarByIdForOwner(String carId, String userEmail) {
        try {
            if (!userRepository.findById(userEmail).orElseThrow().getOwnCars()
                    .contains(carId)) {
                throw new RepositoryException("no such car owned by user");
            }
            FullCarEntity entity = carRepository.findById(carId).orElseThrow(
                    () -> new NotFoundServiceException(String.format("Car with id %s not found in carRepository", carId))
            );
            FullCarDTOResponse response = CarMapper.INSTANCE.mapWithoutOwnerFullBookedPeriods(entity);
            return Optional.of(response);
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * status - ready
     *
     * @param userEmail as Owner
     * @return List<FullCarDTOResponse> Owner cars
     * @author - izum286
     */
    @Override
    public List<FullCarDTOResponse> ownerGetCars(String userEmail) {
        try {
            List<String> ownerCars = userRepository.findById(userEmail).orElseThrow(
                    () -> new NotFoundServiceException(String.format("User %s not found", userEmail))
            ).getOwnCars();
            if (ownerCars == null || ownerCars.isEmpty()) {
                return Collections.emptyList();
            }
            List<FullCarEntity> cars = ownerCars.stream()
                    .map(carId -> carRepository.findById(carId).orElseThrow()).collect(Collectors.toList());
            return cars.stream()
                    .filter(car -> !car.isDeleted())
                    .map(CarMapper.INSTANCE::mapWithoutOwnerFullBookedPeriods)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }


    /**
     * status - ready
     * code cleanup by izum286
     *
     * @return true\false
     */
    @Override
    public List<BookedPeriodDto> getBookedPeriodsByCarId(String carId, String userEmail) {
        try {
            if (!userRepository.findById(userEmail).orElseThrow(
                    () -> new NotFoundServiceException(String.format("User %s not found", userEmail))
            ).getOwnCars()
                    .contains(carId)) {
                throw new NotFoundServiceException("no such car owned by user");
            }
            List<BookedPeriodEntity> list = carRepository.findById(carId).orElseThrow(
                    () -> new NotFoundServiceException(String.format("Car %s not found in carRepo", carId))
            )
                    .getBookedPeriods();
            if (list == null) {
                return new ArrayList<>();
            }
            return list.stream().map(BookedPeriodMapper.INSTANCE::map)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     *
     * @return BookResponseDTO
     */
    @Override
    public Optional<BookResponseDTO> makeReservation(String carId, BookRequestDTO dto, String userEmail) {
        //TODO CarStatistics ??? To Rodion add to protocol rating
        try {
            FullCarEntity carToBookEntity = carRepository.findById(carId).orElseThrow(
                    () -> new NotFoundServiceException(String.format("Car %s not found in carRepo", carId))
            );
            UserEntity userWhoBooked = userRepository.findById(userEmail).orElseThrow(
                    () -> new NotFoundServiceException(String.format("User %s not found", userEmail))
            );
            List<BookedPeriodEntity> carListBookedPeriodEntity =
                    carToBookEntity.getBookedPeriods() == null ? new ArrayList<>() : carToBookEntity.getBookedPeriods();
            List<BookedPeriodEntity> userHistoryBookedEntity =
                    userWhoBooked.getHistory() == null ? new ArrayList<>() : userWhoBooked.getHistory();

            BookedPeriodEntity newPeriod = BookedPeriodMapper.INSTANCE.map(dto);
            newPeriod.setCarId(carId);
            //TODO check amount logic, PricePErDaySimpl - null
            newPeriod.setAmount(carToBookEntity.getPricePerDay().getValue());
            carListBookedPeriodEntity.add(newPeriod);
            carToBookEntity.setBookedPeriods(carListBookedPeriodEntity);
            userHistoryBookedEntity.add(newPeriod);
            userWhoBooked.setHistory(userHistoryBookedEntity);
            //Set stats
            CarStatEntity currStat = carToBookEntity.getStatistics();
            if (currStat == null) {
                currStat = new CarStatEntity(0,0);
            }
            currStat.setTrips(currStat.getTrips() + 1);
            //TODO random Stats raiting
            currStat.setRating((Math.random()*5 + 1));
            carToBookEntity.setStatistics(currStat);
            carRepository.save(carToBookEntity);
            userRepository.save(userWhoBooked);
            BookResponseDTO responseDto = BookedPeriodMapper.INSTANCE.mapToResponse(newPeriod);
            return Optional.of(responseDto);
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }
    }

    /**
     * status - ready
     * code cleanup by izum286
     *
     * @return list of three most popular cars
     */
    @Override
    public List<FullCarDTOResponse> getThreeBestCars() {
        try {
            List<FullCarEntity> fullCarEntityList = carRepository.findAll().stream()
                    .sorted(Comparator.comparing(FullCarEntity::getTrips).reversed())
                    .limit(3)
                    .collect(Collectors.toList());
            return fullCarEntityList.stream().map(CarMapper.INSTANCE::map)
                    .collect(Collectors.toList());
        } catch (Throwable t) {
            throw new ServiceException(t.getMessage(), t.getCause());
        }


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
