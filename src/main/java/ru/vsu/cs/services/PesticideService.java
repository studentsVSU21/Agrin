package ru.vsu.cs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.ExcessVolume;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.AddingPesticideDTO;
import ru.vsu.cs.DTO.ChangeVolumePesticideDTO;
import ru.vsu.cs.Entities.Pesticide;
import ru.vsu.cs.reposirories.PesticideRepository;

import java.util.Optional;


@Service
public class PesticideService {

    private PesticideRepository pesticideRepository;

    @Autowired
    public PesticideService(
            PesticideRepository pesticideRepository
    ){
        this.pesticideRepository = pesticideRepository;
    }

    public Pesticide getPesticideById(Long id) throws NotFoundById {
        Optional<Pesticide> optionalPesticide = pesticideRepository.findById(id);
        if (!optionalPesticide.isPresent()) {
            throw new NotFoundById("Pesticide");
        }
        return optionalPesticide.get();
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

    public void checkVolumeAvailable(Pesticide pesticide, double volume ) throws ExcessVolume {
        if (pesticide.getVolume() < volume ) {
            throw new ExcessVolume();
        }
    }

    public void subtractVolume(Pesticide pesticide, double volume) {
        pesticide.setVolume(pesticide.getVolume() - volume);
        pesticideRepository.save(pesticide);
    }

    public void handleSubtractionVolumeToPesticide(ChangeVolumePesticideDTO changeVolumePesticideDTO) throws NotFoundById, ExcessVolume {
        Pesticide pesticide = getPesticideById(changeVolumePesticideDTO.getPesticideID());
        checkVolumeAvailable(pesticide, changeVolumePesticideDTO.getVolume());
        subtractVolume(pesticide, changeVolumePesticideDTO.getVolume());
    }

    public void handleAddingVolumeToPesticide(ChangeVolumePesticideDTO changeVolumePesticideDTO) throws NotFoundById {
        Pesticide pesticide = getPesticideById(changeVolumePesticideDTO.getPesticideID());
        addVolume(pesticide, changeVolumePesticideDTO.getVolume());
    }

    public void addVolume(Pesticide pesticide, double volume) {
        pesticide.setVolume(pesticide.getVolume() + volume);
        pesticideRepository.save(pesticide);
    }

}