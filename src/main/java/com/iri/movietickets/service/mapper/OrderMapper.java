package com.iri.movietickets.service.mapper;

import com.iri.movietickets.model.Order;
import com.iri.movietickets.model.Ticket;
import com.iri.movietickets.model.dto.OrderResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderResponseDto convertToDto(Order order) {
        OrderResponseDto orderResponseDto =
                new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        List<Long> ticketsId = order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        orderResponseDto.setTicketIds(ticketsId);
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        orderResponseDto.setUserId(order.getUser().getId());
        return orderResponseDto;
    }
}
