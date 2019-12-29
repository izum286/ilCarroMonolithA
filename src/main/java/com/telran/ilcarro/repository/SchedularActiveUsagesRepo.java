package com.telran.ilcarro.repository;

import com.telran.ilcarro.model.car.probably_unused.SchedularUsageDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SchedularActiveUsagesRepo {
    Map<UUID, SchedularUsageDTO> repo = new ConcurrentHashMap<>();

    public SchedularUsageDTO getEntity(double carId) {
        return repo.get(carId);
    }

    public void setEntity(UUID carId, SchedularUsageDTO usageDTO) { repo.put(carId, usageDTO); }

    public void removeEntity(UUID carId) {
        repo.remove(carId);
    }

    public List<SchedularUsageDTO> getAll() {
        ArrayList<SchedularUsageDTO> list = new ArrayList<>(repo.values());
        return list;
    }
}
