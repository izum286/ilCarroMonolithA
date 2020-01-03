package com.telran.ilcarro.service;

import com.telran.ilcarro.service.exceptions.NotReturnedInTimeException;
import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import com.telran.ilcarro.repository.CarRepositoryImpl;
import com.telran.ilcarro.repository.SchedularActiveUsagesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * this class automatically verifies that all vehicles are returned no later than the required date.
 * IMHO need to be a separated service, and starts according to load balancer permission,
 * or must be invoked by load balancer.
 */
@Service
public class ObserverService {

    @Autowired
    SchedularActiveUsagesRepo activeUsagesRepo;

    @Autowired
    CarRepositoryImpl carRepositoryImpl;

    /**
     * TODO
     * need to think - how many threads we need
     */
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

    Runnable task = () -> {
        List<SchedularUsageDTO> list = activeUsagesRepo.getAll();
        //todo for izum286 - mocked
//        for (SchedularUsageDTO r : list) {
//            if (r.getEndDate().isAfter(LocalDate.now()) && carRepositoryImpl.getCarByIdForUsers(r.getCarId()).isRented()) {
//                throw new NotReturnedInTimeException("WARNING!!! car " + r.getCarId() + " not returned in time!!");
//            }
//        }
    };

    /**
     * automatically run when starting application
     * @PostConstruct not necessary if we transfer control of this service to load balancer.
     */
    @PostConstruct //
    public void run() {
        pool.schedule(task, 1, TimeUnit.DAYS);
    }

}
