package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.Entities.ExpenseReport;
import ru.vsu.cs.Entities.Pesticide;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.reposirories.ExpenseReportRepository;

import java.time.LocalDate;
import java.util.Collection;

@Service
public class ExpenseReportService {

    private final Logger LOG = LoggerFactory.getLogger(ExpenseReportService.class);

    private ExpenseReportRepository expenseReportRepository;

    @Autowired
    public ExpenseReportService(
            ExpenseReportRepository reportRepository
    ){
        this.expenseReportRepository = reportRepository;
    }

    public Collection<ExpenseReport> getExpenseReportByProgress(Progress progress) {
        LOG.debug("progress: {}", progress);
        return expenseReportRepository.findByProgress(progress);
    }

    public void createExpenseReport(Progress progress, Pesticide pesticide, double volume) {
        ExpenseReport expenseReport = new ExpenseReport();
        expenseReport.setPesticide(pesticide);
        expenseReport.setVolume(volume);
        expenseReport.setProgress(progress);
        expenseReport.setReportDate(LocalDate.now());
        expenseReportRepository.save(expenseReport);
        LOG.debug("Expense report : {}", expenseReport);
    }
}