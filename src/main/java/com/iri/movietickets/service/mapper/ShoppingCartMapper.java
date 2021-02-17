package com.iri.movietickets.service.mapper;

import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.Ticket;
import com.iri.movietickets.model.dto.ShoppingCartResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto convertFromShoppingCart(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto =
                new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserShoppingCartId(shoppingCart.getId());
        List<Long> ticketsId = shoppingCart.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTicketIds(ticketsId);
        return shoppingCartResponseDto;
    }
}
