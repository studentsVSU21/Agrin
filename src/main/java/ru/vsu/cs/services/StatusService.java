package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.Entities.Status;
import ru.vsu.cs.reposirories.StatusRepository;

import java.util.Optional;

@Service
public class StatusService {
    private final Logger LOG = LoggerFactory.getLogger(StatusService.class);

    private StatusRepository statusRepository;

    @Autowired
    public StatusService(
        StatusRepository statusRepository
    ){
        this.statusRepository = statusRepository;
    }

    public Status getStatusById(Long id) throws NotFoundById {
        Optional<Status> optionalStatus = statusRepository.findById(id);
        if (!optionalStatus.isPresent()) {
            throw new NotFoundById("Status");
        }
        return optionalStatus.get();
    }
}
