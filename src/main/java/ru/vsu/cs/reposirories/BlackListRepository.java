package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.BlackList;

import java.util.Collection;

@Repository
public interface BlackListRepository extends CrudRepository<BlackList, Long> {

    Collection<BlackList> findByEmail(String mail);
    Collection<BlackList> findByNumberPhone(String phoneNumber);

}
