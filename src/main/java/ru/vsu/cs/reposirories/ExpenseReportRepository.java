package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.ExpenseReport;
import ru.vsu.cs.Entities.Progress;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ExpenseReportRepository  extends CrudRepository<ExpenseReport, Long> {

    Collection<ExpenseReport> findByProgress(Progress progress);

}
