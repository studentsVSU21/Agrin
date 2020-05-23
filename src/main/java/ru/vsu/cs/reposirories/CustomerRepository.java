package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
