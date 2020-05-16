package ru.vsu.cs.reposirories;

import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String mail);

}
