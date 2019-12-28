package com.telran.ilcarro.service;

import com.telran.ilcarro.model.web.*;
import com.telran.ilcarro.repository.CarRepo;
import com.telran.ilcarro.repository.entity.FullCarEntity;
import com.telran.ilcarro.repository.entity.SchedularUsageEntity;
import com.telran.ilcarro.repository.exception.ConflictRepositoryException;
import com.telran.ilcarro.repository.exception.NotFoundRepositoryException;
import com.telran.ilcarro.repository.exception.RepositoryException;
import com.telran.ilcarro.service.converters.FullCarDtoEntityConverter;
import com.telran.ilcarro.service.converters.FullCarEntityToShortCarEntityConverter;
import com.telran.ilcarro.service.converters.SchedularUsageListDtoEntityConverter;
import com.telran.ilcarro.service.converters.ShortCarDtoEntityConverter;
import com.telran.ilcarro.service.exceptions.ConflictServiceException;
import com.telran.ilcarro.service.exceptions.NotFoundServiceException;
import com.telran.ilcarro.service.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarRepo carRepository;

    @Override
    public Optional<ShortCarDTO> addCar(FullCarDTOResponse carDTO) {
        try {
            FullCarEntity entity = carRepository.addCar(FullCarDtoEntityConverter.map(carDTO));
            return Optional.of(ShortCarDtoEntityConverter.map(FullCarEntityToShortCarEntityConverter.map(entity)));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }

    }

    @Override
    public Optional<ShortCarDTO> updateCar(FullCarDTOResponse carDTO) {
        try {
            FullCarEntity entity = carRepository.updateCar(FullCarDtoEntityConverter.map(carDTO));
            return Optional.of(ShortCarDtoEntityConverter.map(FullCarEntityToShortCarEntityConverter.map(entity)));
        } catch (ConflictRepositoryException ex) {
            throw new ConflictServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public boolean deleteCar(String id) {
        try {
            carRepository.deleteCar(UUID.fromString(id));
            return true;
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public Optional<FullCarDTOResponse> getCarByIdForUsers(String id) {
        try {
            FullCarEntity entity = carRepository.getCarByIdForUsers(UUID.fromString(id));
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
    public Optional<FullCarDTOResponse> ownerGetCarById(String id) {
        try {
            FullCarEntity entity = carRepository.ownerGetCarById(UUID.fromString(id));
            return Optional.of(FullCarDtoEntityConverter.map(entity));
        } catch (RepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        }
    }


    @Override
    public List<SchedularUsageDTO> ownerGetBookedPeriodsByCarId(String id) {
        try {
            List<SchedularUsageEntity> schedularUsageEntityList = carRepository.ownerGetBookedPeriodsByCarId(UUID.fromString(id));
            return schedularUsageEntityList.stream()
                    .map(SchedularUsageListDtoEntityConverter::map)
                    .collect(Collectors.toList());
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public BookedPeriodsDto makeReservation(String id, Make_A_Reservation_DataParamsDto dto) {
        try {
            BookedPeriodsDto bookedPeriodsDto = new BookedPeriodsDto(dto.getStart_date_time(), dto.getEnd_date_time(), dto.getPersonWhoBookedDto());
            FullCarEntity entity = carRepository.getCarByIdForUsers(UUID.fromString(id));
            entity.setRented(true);
            carRepository.updateCar(entity);
            return bookedPeriodsDto;
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }
}
