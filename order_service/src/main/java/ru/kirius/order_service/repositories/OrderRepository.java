package ru.kirius.order_service.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.kirius.order_service.entities.Order;

import java.util.Optional;

@Repository
public interface OrderRepository extends ListCrudRepository<Order, Long> {
    Optional<Order> findByOrderNumber(@NonNull long orderNumber);
}