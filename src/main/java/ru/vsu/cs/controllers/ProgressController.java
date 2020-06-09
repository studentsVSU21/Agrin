package ru.vsu.cs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.ExcessVolume;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.ExpenseReportDTO;
import ru.vsu.cs.DTO.ResponseExpenseReportDTO;
import ru.vsu.cs.DTO.StatusResponse;
import ru.vsu.cs.DTO.mappers.ResponseExpenseReportMapper;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.services.ProgressService;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    private static final Logger LOG = LoggerFactory.getLogger(ProgressController.class);

    private ProgressService progressService;
    private ResponseExpenseReportMapper responseExpenseReportMapper;

    @Autowired
    public ProgressController(
            ProgressService progressService,
            ResponseExpenseReportMapper responseExpenseReportMapper) {
        this.progressService = progressService;
        this.responseExpenseReportMapper = responseExpenseReportMapper;
    }


    @GetMapping("/{id}")
    public Progress getProgressByID(@PathVariable(value = "id") Long id) {

        Progress progress = progressService.getProgressByID(id);

        return progress;
    }

    @PostMapping("/create/expense/report")
    @CrossOrigin
    public StatusResponse createExpenseReport(@RequestBody ExpenseReportDTO expenseReportDTO) {
        LOG.debug("create Expense Report");
        try {
            progressService.createExpenseReport(expenseReportDTO);
            return StatusResponse.successResponse();
        } catch (NotFoundById | ExcessVolume ex) {
            LOG.error("err", ex);
            return StatusResponse.failureResponse();
        }
    }

    @GetMapping("/expense/reports")
    @CrossOrigin
    public Collection<ResponseExpenseReportDTO> getExpenseReports(@RequestParam(name = "progressId") Long id) {
        LOG.debug("/expense/reports");
        try {
            return progressService.getExpenseReports(id)
                    .stream()
                    .map(responseExpenseReportMapper::toDto)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (NotFoundById notFoundById) {
            return Collections.emptyList();
        }
    }
}