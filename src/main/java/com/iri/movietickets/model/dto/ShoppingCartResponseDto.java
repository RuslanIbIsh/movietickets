package com.iri.movietickets.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long userShoppingCartId;
    private List<Long> ticketIds;

    public Long getUserShoppingCartId() {
        return userShoppingCartId;
    }

    public void setUserShoppingCartId(Long userShoppingCartId) {
        this.userShoppingCartId = userShoppingCartId;
    }

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }
}
