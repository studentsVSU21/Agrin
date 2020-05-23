package ru.vsu.cs.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.reposirories.ProgressRepository;

import java.util.Optional;

@Service
public class ProgressService {

    private static final Logger LOG = LoggerFactory.getLogger(ProgressService.class);

    private ProgressRepository progressRepository;


    @Autowired
    public ProgressService(
            ProgressRepository progressRepository)
    {
        this.progressRepository = progressRepository;
    }


    public Progress getProgressByID(Long progressID) {

        LOG.debug("Service get Progress by ID");

        Optional<Progress> progress = progressRepository.findById(progressID);

        if (progress.isPresent()) {
            LOG.debug("Progress : {}", progress.get());

            return progress.get();
        }
        return null;
    }

}
