package ru.vsu.cs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.ExcessVolume;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.AddingPesticideDTO;
import ru.vsu.cs.DTO.ChangeVolumePesticideDTO;
import ru.vsu.cs.DTO.StatusResponse;
import ru.vsu.cs.Entities.Pesticide;
import ru.vsu.cs.services.PesticideService;

@RestController
@RequestMapping("/pesticide/manage")
public class PesticideManageController {

    private final Logger LOG = LoggerFactory.getLogger(PesticideManageController.class);

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

    @PostMapping("/volume/add")
    @CrossOrigin
    public StatusResponse addVolumeToPesticide(@RequestBody ChangeVolumePesticideDTO changeVolumePesticideDTO) {
        LOG.debug("volume add");
        try {
            pesticideService.handleAddingVolumeToPesticide(changeVolumePesticideDTO);
            return StatusResponse.successResponse();
        } catch (NotFoundById notFoundById) {
            return StatusResponse.failureResponse();
        }
    }

    @PostMapping("/volume/subtract")
    @CrossOrigin
    public StatusResponse subtractVolumeToPesticide(@RequestBody ChangeVolumePesticideDTO changeVolumePesticideDTO) {
        LOG.debug("volume add");
        try {
            pesticideService.handleSubtractionVolumeToPesticide(changeVolumePesticideDTO);
            return StatusResponse.successResponse();
        } catch (NotFoundById | ExcessVolume ex) {
            LOG.debug("error ", ex);
            return StatusResponse.failureResponse();
        }
    }
}