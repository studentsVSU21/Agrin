package ru.vsu.cs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.StatusResponse;
import ru.vsu.cs.services.BlackListService;
import ru.vsu.cs.services.OrderService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final Logger LOG = LoggerFactory.getLogger(ManagerController.class);

    private BlackListService blackListService;
    private OrderService orderService;

    @Autowired
    public ManagerController(
            BlackListService blackListService,
            OrderService orderService
    ) {
        this.orderService = orderService;
        this.blackListService = blackListService;
    }

    @PostMapping("/block/user")
    @CrossOrigin
    public StatusResponse blockUserController(
            @RequestParam("email") String email,
            @RequestParam("phoneNumber") String phone)
    {
        LOG.debug("Block user");
        LOG.debug("Email : {}, email, phone : {}", email, phone);
        blackListService.blockUser(email, phone);
        return StatusResponse.successResponse();
    }

    @PostMapping("/order/block/data")
    @CrossOrigin
        public StatusResponse blockOrderDataController(@RequestParam("orderId") Long orderID) {
        LOG.debug("Block order data");
        try {
            orderService.blockDataOrder(orderID);
            return StatusResponse.successResponse();
        } catch (NotFoundById notFoundById) {
            LOG.error("error", notFoundById);
            return StatusResponse.failureResponse();
        }
    }
}