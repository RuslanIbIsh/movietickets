package com.iri.movietickets.controller;

import com.iri.movietickets.model.ShoppingCart;
import com.iri.movietickets.model.User;
import com.iri.movietickets.model.dto.OrderResponseDto;
import com.iri.movietickets.service.OrderService;
import com.iri.movietickets.service.ShoppingCartService;
import com.iri.movietickets.service.UserService;
import com.iri.movietickets.service.mapper.OrderMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private OrderService orderService;
    private OrderMapper orderMapper;

    @Autowired
    public OrderController(UserService userService,
                           ShoppingCartService shoppingCartService,
                           OrderService orderService,
                           OrderMapper orderMapper) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestParam Long userId) {
        User user = userService.getById(userId);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart);
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersHistory(@RequestParam Long userId) {
        User user = userService.getById(userId);
        return orderService.getOrderHistory(user).stream()
                .map(order -> orderMapper.convertToDto(order))
                .collect(Collectors.toList());
    }
}
