package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Region;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
}
