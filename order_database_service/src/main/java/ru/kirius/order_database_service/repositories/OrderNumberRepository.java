package ru.kirius.order_database_service.repositories;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import ru.kirius.order_database_service.entities.OrderNumber;

import java.util.Optional;

@Repository
public interface OrderNumberRepository extends ListCrudRepository<OrderNumber, Integer> {
    Optional<OrderNumber> findByNum(@NonNull int num);
}