package ru.vsu.cs.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.Entities.Status;
import ru.vsu.cs.reposirories.ProgressRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Optional;

@Service
public class ProgressService {

    private static final Logger LOG = LoggerFactory.getLogger(ProgressService.class);

    private ProgressRepository progressRepository;
    private StatusService statusService;

    @Autowired
    public ProgressService(
            ProgressRepository progressRepository,
            StatusService statusService)
    {
        this.progressRepository = progressRepository;
        this.statusService = statusService;
    }


    public Progress initProgress() throws NotFoundById {
        Progress progress = new Progress();
        LOG.debug("invoke get Status");
        progress.setStatus(statusService.getStatusById(1L));
        LOG.debug("after get Status");
        progress.setProcessedArea(0.0);
        progressRepository.save(progress);
        LOG.debug("COmpleted init progress : {}", progress);
        return progress;
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

    public Collection<Progress> getNewProgress() throws NotFoundById{
        Status status = statusService.getStatusById(1L);
        return progressRepository.findAllByStatus(status);
    }

    public void assumeStatusRejected(Progress progress) throws NotFoundById {
        Status status = statusService.getStatusById(4L);
        progress.setStatus(status);
        progressRepository.save(progress);
    }
}