package com.example.demo.service;

import com.example.demo.model.Load;
import com.example.demo.repository.LoadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoadService {
    private final LoadRepository loadRepository;

    public LoadService(LoadRepository loadRepository) {
        this.loadRepository = loadRepository;
    }

    public Load createLoad(Load load) {
        return loadRepository.save(load);
    }

    public List<Load> getFilteredLoads(String shipperId, String truckType, String productType, String loadingPoint, String unloadingPoint) {
        List<Load> allLoads = loadRepository.findAll();

        return allLoads.stream()
            .filter(load -> shipperId == null || shipperId.equals(load.getShipperId()))
            .filter(load -> truckType == null || truckType.equals(load.getTruckType()))
            .filter(load -> productType == null || productType.equals(load.getProductType()))
            .filter(load -> loadingPoint == null || loadingPoint.equals(load.getFacility().getLoadingPoint()))
            .filter(load -> unloadingPoint == null || unloadingPoint.equals(load.getFacility().getUnloadingPoint()))
            .toList();
    }

    public Optional<Load> getLoadById(String loadId) {
        return loadRepository.findById(loadId);
    }

    public Load updateLoad(String loadId, Load updatedLoad) {
        updatedLoad.setLoadId(loadId);
        return loadRepository.save(updatedLoad);
    }

    public void deleteLoad(String loadId) {
        loadRepository.deleteById(loadId);
    }
}