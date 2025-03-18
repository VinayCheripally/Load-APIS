package com.example.demo.controller;

import com.example.demo.model.Load;
import com.example.demo.service.LoadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/load")
public class LoadController {
    private final LoadService loadService;

    public LoadController(LoadService loadService) {
        this.loadService = loadService;
    }

    @PostMapping
    public ResponseEntity<Load> createLoad(@RequestBody Load load) {
        Load createdLoad = loadService.createLoad(load);
        return new ResponseEntity<>(createdLoad, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Load> getLoads(
        @RequestParam(required = false) String shipperId,
        @RequestParam(required = false) String truckType,
        @RequestParam(required = false) String productType,
        @RequestParam(required = false) String loadingPoint,
        @RequestParam(required = false) String unloadingPoint
    ) {
        return loadService.getFilteredLoads(shipperId, truckType, productType, loadingPoint, unloadingPoint);
    }

    @GetMapping("/{loadId}")
    public Optional<Load> getLoadById(@PathVariable String loadId) {
        return loadService.getLoadById(loadId);
    }

    @PutMapping("/{loadId}")
    public Load updateLoad(@PathVariable String loadId, @RequestBody Load updatedLoad) {
        return loadService.updateLoad(loadId, updatedLoad);
    }

    @DeleteMapping("/{loadId}")
    public void deleteLoad(@PathVariable String loadId) {
        loadService.deleteLoad(loadId);
    }
}
