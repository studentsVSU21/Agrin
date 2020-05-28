package ru.vsu.cs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.DTO.AddingPesticideDTO;
import ru.vsu.cs.DTO.StatusResponse;
import ru.vsu.cs.Entities.Pesticide;
import ru.vsu.cs.services.PesticideService;

@RestController
@RequestMapping("/pesticide/manage")
public class PesticideManageController {

    private PesticideService pesticideService;

    @Autowired
    public PesticideManageController(
            PesticideService pesticideService
    ) {
        this.pesticideService = pesticideService;
    }

    @PostMapping("/add")
    @CrossOrigin
    public StatusResponse addPesticide(@RequestBody AddingPesticideDTO addingPesticideDTO) {
        this.pesticideService.AddingPesticide(addingPesticideDTO);
        return new StatusResponse("success");
    }

    @GetMapping("/all")
    @CrossOrigin
    public Iterable<Pesticide> getAllPesticide() {
        return pesticideService.getAllPesticide();
    }
}