package ru.vsu.cs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.Entities.Region;
import ru.vsu.cs.services.RegionService;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@RequestMapping("/region")
public class RegionController {

    private final Logger LOG = LoggerFactory.getLogger(RegionController.class);

    private RegionService regionService;

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods" , "GET, PUT, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
    }

    @Autowired
    public RegionController(
        RegionService regionService
    ){
        this.regionService = regionService;
    }

    @GetMapping("/list")
    @ResponseBody
    public Collection<Region> getRegions(){
        return regionService.getRegions();
    }
}