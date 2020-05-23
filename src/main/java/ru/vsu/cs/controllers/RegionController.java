package ru.vsu.cs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.Entities.Region;
import ru.vsu.cs.services.RegionService;

import java.util.Collection;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final Logger LOG = LoggerFactory.getLogger(RegionController.class);

    private RegionService regionService;

    @Autowired
    public RegionController(
        RegionService regionService
    ){
        this.regionService = regionService;
    }

    @GetMapping("/list")
    public Collection<Region> getRegions(){
        return regionService.getRegions();
    }

}
