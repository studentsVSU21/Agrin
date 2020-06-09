package ru.vsu.cs.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.vsu.cs.CustomExceptions.ExcessVolume;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.ExpenseReportDTO;
import ru.vsu.cs.Entities.ExpenseReport;
import ru.vsu.cs.Entities.Pesticide;
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
    private ExpenseReportService expenseReportService;
    private PesticideService pesticideService;

    @Autowired
    public ProgressService(
            ProgressRepository progressRepository,
            StatusService statusService,
            ExpenseReportService expenseReportService,
            PesticideService pesticideService)
    {
        this.progressRepository = progressRepository;
        this.statusService = statusService;
        this.expenseReportService = expenseReportService;
        this.pesticideService = pesticideService;
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

    public void assumeStatusAdopted(Progress progress) throws NotFoundById {
        Status status = statusService.getStatusById(2l);
        progress.setStatus(status);
    }

    public Collection<Progress> getApodtedProgress() throws NotFoundById {
        Status status = statusService.getStatusById(2L);
        return progressRepository.findAllByStatus(status);
    }

    public Collection<ExpenseReport> getExpenseReports(Progress progress) {
        return expenseReportService.getExpenseReportByProgress(progress);
    }

    public Progress findProgressById(Long progressID) throws NotFoundById {

        Optional<Progress> progress = progressRepository.findById(progressID);

        if (!progress.isPresent()) {
            throw new NotFoundById("progress");
        }
        return progress.get();
    }

    public void createExpenseReport(ExpenseReportDTO expenseReportDTO) throws NotFoundById, ExcessVolume {
        Progress progress = findProgressById(expenseReportDTO.getProgressId());
        LOG.debug("Progress : {}", progress);
        Pesticide pesticide = pesticideService.getPesticideById(expenseReportDTO.getPesticideId());
        LOG.debug("Pesticide : {}", pesticide);
        pesticideService.checkVolumeAvailable(pesticide, expenseReportDTO.getVolume());
        pesticideService.subtractVolume(pesticide, expenseReportDTO.getVolume());
        LOG.debug("Pesticide : {}", pesticide);
        expenseReportService.createExpenseReport(progress, pesticide, expenseReportDTO.getVolume());
    }

    public Collection<ExpenseReport> getExpenseReports(Long id) throws NotFoundById {
        Progress progress = findProgressById(id);
        return expenseReportService.getExpenseReportByProgress(progress);
    }

    public void progressAssumeStatusComplete(Progress progress) throws NotFoundById {
        LOG.debug("Progress status complete");
        Status status = statusService.getStatusById(3L);
        progress.setStatus(status);
    }
}