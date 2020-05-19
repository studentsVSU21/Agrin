package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
