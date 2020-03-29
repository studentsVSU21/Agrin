package ru.vsu.cs.reposirories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Progress;

@Repository
public interface ProgressRepository extends CrudRepository<Progress, Long> {

}
