package ru.vsu.cs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.NewOrderDTO;
import ru.vsu.cs.DTO.OrderDTO;
import ru.vsu.cs.DTO.StatusResponse;
import ru.vsu.cs.DTO.mappers.NewOrderMapper;
import ru.vsu.cs.Entities.Order;
import ru.vsu.cs.services.OrderService;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;
    private NewOrderMapper newOrderMapper;

    @Autowired
    public OrderController(
            OrderService orderService,
            NewOrderMapper newOrderMapper
    )
    {
        this.orderService = orderService;
        this.newOrderMapper = newOrderMapper;
    }

    @PostMapping("/custom/creation")
    @CrossOrigin
    public StatusResponse createOrder(@RequestBody OrderDTO order) {
        LOG.debug("order : {}", order);
        try {
            LOG.debug("Order : {}", order);
            orderService.createOrder(order);
            return StatusResponse.successResponse();
        }
        catch (NotFoundById ex)
        {
            return StatusResponse.failureResponse();
        }
    }

    @GetMapping("/new/orders")
    @CrossOrigin
    public Collection<NewOrderDTO> getNewOrders() {
        try {
            Collection<NewOrderDTO> orders = orderService.getNewOrder()
                    .stream()
                    .map(order -> (NewOrderDTO) newOrderMapper.toDto(order))
                    .collect(Collectors.toCollection(LinkedList::new));
            return orders;
        }
        catch (NotFoundById ex) {
            return null;
        }
    }

    @PostMapping("/reject")
    @CrossOrigin
    public StatusResponse rejectOrder(@RequestParam("orderId") Long orderId) {
        try {
            orderService.rejectOrder(orderId);
            return new StatusResponse("success");
        }
        catch ( NotFoundById ex) {
            return new StatusResponse("failure");
        }
    }
}