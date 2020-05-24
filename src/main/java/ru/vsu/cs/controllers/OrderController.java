package ru.vsu.cs.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.OrderDTO;
import ru.vsu.cs.DTO.StatusResponse;
import ru.vsu.cs.services.OrderService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private OrderService orderService;

    @Autowired
    public OrderController(
            OrderService orderService
    )
    {
        this.orderService = orderService;
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
}