package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Deprecated
public class SchedularUsagesRepo {
    /**
     * car Id as a key
     */
    Map<UUID, SchedularUsageDTO> repo = new ConcurrentHashMap<>();

    public SchedularUsageDTO getEntity(UUID carId) {
        return repo.get(carId);
    }
}
