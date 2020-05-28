package ru.vsu.cs.reposirories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.Entities.Order;
import ru.vsu.cs.Entities.Progress;

import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Optional<Order> findOrderByProgress(Progress progress);
    Optional<Order> findOrderByProgress(Long progress);
}