package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long> {
}
