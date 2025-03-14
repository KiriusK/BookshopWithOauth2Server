package ru.kirius.order_service.dto;

import ru.kirius.order_service.enums.OrderStatus;

import java.time.LocalDate;

public record OrderDto(
        long orderNumber
        , LocalDate orderDate
        , String consumerName
        , String consumerSurName
        , String country
        , String region
        , String city
        , String street
        , int houseNumber
        , int flatNumber
        , double cost
        , OrderStatus status
) {
}
