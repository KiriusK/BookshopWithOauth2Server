package ru.kirius.order_database_service.services;

import org.springframework.stereotype.Service;
import ru.kirius.order_database_service.entities.OrderNumber;
import ru.kirius.order_database_service.repositories.OrderNumberRepository;

@Service
public class OrderNumberService {

    private final OrderNumberRepository repo;

    public OrderNumberService(OrderNumberRepository repo) {
        this.repo = repo;
    }

    public long getNumber() {
        OrderNumber number = repo.findByNum(1).orElse(new OrderNumber(1, 0));
        return number.getLastOrderNumber();
    }

    public void saveNumber(long orderNumber) {
        OrderNumber number = repo.findByNum(1).orElse(new OrderNumber(1, 0));
        number.setLastOrderNumber(orderNumber);
        repo.save(number);
    }
}
