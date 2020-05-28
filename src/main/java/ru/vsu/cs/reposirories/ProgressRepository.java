package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.Entities.Status;

import java.util.Collection;

@Repository
public interface ProgressRepository extends CrudRepository<Progress, Long> {
    Collection<Progress> findAllByStatus(Status status);
}
