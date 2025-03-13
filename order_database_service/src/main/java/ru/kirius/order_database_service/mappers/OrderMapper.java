package ru.kirius.order_database_service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.kirius.order_database_service.dto.OrderDto;
import ru.kirius.order_database_service.entities.Order;

import java.util.List;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    Order toOrder(OrderDto orderDto);

    OrderDto toOrderDto(Order order);

    List<Order> toOrderList(List<OrderDto> orderDtoList);

    List<OrderDto> toOrderDtoList(List<Order> orderList);

}
