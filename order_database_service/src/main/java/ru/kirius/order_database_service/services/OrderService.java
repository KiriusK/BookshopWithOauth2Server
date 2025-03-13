package ru.kirius.order_database_service.services;

import org.springframework.stereotype.Service;
import ru.kirius.order_database_service.dto.OrderDto;
import ru.kirius.order_database_service.entities.Order;
import ru.kirius.order_database_service.enums.OrderStatus;
import ru.kirius.order_database_service.mappers.OrderMapper;
import ru.kirius.order_database_service.repositories.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;
    private final OrderNumberService numberService;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repo, OrderNumberService numberService, OrderMapper mapper) {
        this.repo = repo;
        this.numberService = numberService;
        this.mapper = mapper;
    }

    public long addOrder(OrderDto orderDto) {
        Order order = mapper.toOrder(orderDto);
        long orderNumber = numberService.getNumber() + 1;
        order.setOrderNumber(orderNumber);
        order.setOrderDate(LocalDate.now());
        order.setStatus(OrderStatus.ACCEPT);
        repo.save(order);
        numberService.saveNumber(orderNumber);
        return orderNumber;
    }

    public List<OrderDto> getAllOrders() {
        return mapper.toOrderDtoList(repo.findAll());
    }

    public boolean updateOrder(OrderDto orderDto) {
        Order order = repo.findByOrderNumber(orderDto.orderNumber()).orElse(null);
        if (order != null) {
            order.setConsumerName(orderDto.consumerName());
            order.setConsumerSurName(orderDto.consumerSurName());
            order.setCountry(orderDto.country());
            order.setRegion(orderDto.region());
            order.setCity(orderDto.city());
            order.setStreet(orderDto.street());
            order.setHouseNumber(orderDto.houseNumber());
            order.setFlatNumber(orderDto.flatNumber());
            order.setCost(orderDto.cost());
            order.setStatus(orderDto.status());
            repo.save(order);
            return true;
        }
        return false;
    }

    public OrderDto getOrderByNumber(long orderNum) {
        Order order = repo.findByOrderNumber(orderNum).orElse(null);
        if (order != null) {
            OrderDto orderDto = mapper.toOrderDto(order);
            return orderDto;
        }
        return null;
    }
}
