package ru.kirius.order_database_service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kirius.order_database_service.dto.OrderDto;
import ru.kirius.order_database_service.entities.Order;
import ru.kirius.order_database_service.enums.OrderStatus;
import ru.kirius.order_database_service.services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;
    private final Logger logger;

    public OrderController(OrderService service) {
        this.service = service;
        logger = LoggerFactory.getLogger(OrderController.class);
    }

    @PostMapping("/admin/add")
    public ResponseEntity<Long> addOrder(@RequestBody OrderDto order) {
        long orderNum = service.addOrder(order);
        if (orderNum > 0) {
            return new ResponseEntity<>(orderNum, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PostMapping("/admin/update")
    public ResponseEntity<String> updateOrder(@RequestBody OrderDto order) {
        if(service.updateOrder(order)) {
            return new ResponseEntity<>("ok", HttpStatus.OK);
        }
        return new ResponseEntity<>("not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/admin/get")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> result = service.getAllOrders();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/getStatus")
    public ResponseEntity<OrderStatus> getOrderStatus(@RequestParam("order") long orderNum) {
        OrderDto order = service.getOrderByNumber(orderNum);
        if(order != null) {
            return new ResponseEntity<>(order.status(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getOrder")
    public ResponseEntity<OrderDto> getOrderByNumber(@RequestParam("order") long orderNum) {
        OrderDto order = service.getOrderByNumber(orderNum);
        if(order != null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exHandler(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }
}
