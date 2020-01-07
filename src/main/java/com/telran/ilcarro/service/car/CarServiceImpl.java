package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.*;
import com.telran.ilcarro.model.user.OwnerDtoResponse;
import com.telran.ilcarro.repository.CarRepository;
import com.telran.ilcarro.repository.entity.BookedPeriodEntity;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.converters.FullCarDtoEntityConverter;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import com.telran.ilcarro.service.mapper.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    MapperService mapperService;

    @Override
    public Optional<FullCarDTOResponse> addCar(AddUpdateCarDtoRequest carToAdd) {
        try {
            FullCarEntity entity = carRepository.addCar(carToAdd);
            return Optional.of(mapperService.map(entity));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public Optional<FullCarDTOResponse> updateCar(AddUpdateCarDtoRequest carToUpdate) {
        try {
            FullCarEntity entity = carRepository.updateCar(carToUpdate);
            return Optional.of(mapperService.map(entity));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public boolean deleteCar(String carId) {
        try {
            carRepository.deleteCar(UUID.fromString(carId));
            return true;
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Optional<FullCarDTOResponse> getCarById(String carId) {
        try {
            FullCarEntity entity = carRepository.getCarByIdForUsers(UUID.fromString(carId));
            return Optional.of(FullCarDtoEntityConverter.map(entity));
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }


    @Override
    public List<FullCarDTOResponse> ownerGetCars() {
        try {
            List<FullCarEntity> fullCarEntityList = carRepository.ownerGetCars();
            return fullCarEntityList.stream()
                    .map(FullCarDtoEntityConverter::map)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }


    @Override
    public List<BookedPeriodDto> getBookedPeriodsByCarId(String carId) {
        try {
            return carRepository.ownerGetBookedPeriodsByCarId(carId);
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Optional<BookResponseDTO> makeReservation(String carId, BookRequestDTO dto) {
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

    @Override
    public List<FullCarDTOResponse> get3BestCars() {
        try {
            List<FullCarEntity> fullCarEntityList = carRepository.getTopByBookedPeriods();
            return fullCarEntityList.stream()
                    .map(FullCarDtoEntityConverter::map)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
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
