package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.web.SchedularUsageDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SchedularUsagesRepo {
    /**
     * car Id as a key
     */
    Map<UUID, SchedularUsageDTO> repo = new ConcurrentHashMap<>();

    public SchedularUsageDTO getEntity(UUID carId) {
        return repo.get(carId);
    }
}
