package de.oth.regensburg.projektstudium.backend.controller;

import de.oth.regensburg.projektstudium.backend.entity.Building;
import de.oth.regensburg.projektstudium.backend.service.BuildingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class BuildingController {

    private static final Logger log = LoggerFactory.getLogger(BuildingController.class);
    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/buildings")
    Flux<Building> findAll() {
        return buildingService.findAll();
    }

    @GetMapping("/buildings/{id}")
    Mono<Building> findOneById(@PathVariable("id") String id) {
        return buildingService.findOneById(id);
    }
}
