package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Pesticide;

@Repository
public interface PesticideRepository extends CrudRepository<Pesticide, Long> {
}
