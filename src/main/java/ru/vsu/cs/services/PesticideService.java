package ru.vsu.cs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.DTO.AddingPesticideDTO;
import ru.vsu.cs.Entities.Pesticide;
import ru.vsu.cs.reposirories.PesticideRepository;


@Service
public class PesticideService {

    private PesticideRepository pesticideRepository;

    @Autowired
    public PesticideService(
            PesticideRepository pesticideRepository
    ){
        this.pesticideRepository = pesticideRepository;
    }

    public void AddingPesticide(AddingPesticideDTO addingPesticideDTO) {
        Pesticide pesticide = new Pesticide(
                addingPesticideDTO.getName(),
                addingPesticideDTO.getVolume()
        );
        pesticideRepository.save(pesticide);
    }

    public Iterable<Pesticide> getAllPesticide() {
        return pesticideRepository.findAll();
    }
}
