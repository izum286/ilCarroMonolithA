package com.telran.ilcarro.service.car;

import com.telran.ilcarro.model.car.BookRequestDTO;
import com.telran.ilcarro.model.car.BookedPeriodDto;
import com.telran.ilcarro.model.car.FullCarDTOResponse;
import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import com.telran.ilcarro.model.car.probably_unused.ShortCarDTO;
import com.telran.ilcarro.repository.CarRepository;
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
    CarRepository carRepository;

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
    public List<SchedularUsageDTO> ownerGetBookedPeriodsByCarId(String carId) {
        try {
            List<SchedularUsageEntity> schedularUsageEntityList = carRepository.ownerGetBookedPeriodsByCarId(UUID.fromString(carId));
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
    public BookedPeriodDto makeReservation(String carId, BookRequestDTO dto) {
        try {
            BookedPeriodDto bookedPeriodDto = new BookedPeriodDto(dto.getStartDateTime(), dto.getEndDateTime(), dto.getPersonWhoBookedDto());
            FullCarEntity entity = carRepository.getCarByIdForUsers(UUID.fromString(carId));
            entity.setRented(true);
            carRepository.updateCar(entity);
            return bookedPeriodDto;
        } catch (NotFoundRepositoryException ex) {
            throw new NotFoundServiceException(ex.getMessage(), ex.getCause());
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage(), ex.getCause());
        }
    }
}
