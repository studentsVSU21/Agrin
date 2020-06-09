package ru.vsu.cs.controllers;


import org.modelmapper.Converters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.CustomExceptions.ExcessVolume;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.*;
import ru.vsu.cs.DTO.mappers.InfoOrderMapper;
import ru.vsu.cs.DTO.mappers.NewOrderMapper;
import ru.vsu.cs.Entities.Order;
import ru.vsu.cs.Entities.Pesticide;
import ru.vsu.cs.services.OrderService;

import javax.servlet.http.HttpServletResponse;
import java.text.CollationElementIterator;
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
    private InfoOrderMapper infoOrderMapper;

    @Autowired
    public OrderController(
            OrderService orderService,
            NewOrderMapper newOrderMapper,
            InfoOrderMapper infoOrderMapper
    )
    {
        this.infoOrderMapper = infoOrderMapper;
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

    @PostMapping("/user/create")
    @CrossOrigin
    public StatusResponse addingOrder(@RequestBody OrderDTO order) {
        try {
            orderService.createUserOrder(order);
            return StatusResponse.successResponse();
        } catch (NotFoundById notFoundById) {
            LOG.error("Not found", notFoundById);
            return StatusResponse.failureResponse();
        }
    }

    @PostMapping("/confirm")
    @CrossOrigin
    public StatusResponse confirmOrder(@RequestBody NotConfirmedOrderDTO notConfirmedOrderDTO) throws NotFoundById {
        LOG.debug("notConfirmedOrderDTO : {}", notConfirmedOrderDTO);
        try {
            orderService.confirmOrder(notConfirmedOrderDTO);
            return StatusResponse.successResponse();
        }
        catch (NotFoundById ex) {
            LOG.error("Exception : {}", ex.getMessage(), ex);
            return StatusResponse.failureResponse();
        }
    }

    @GetMapping("current")
    @CrossOrigin
    public Collection<NewOrderDTO> getCurrentOrder() {
        try {
            return orderService.getAdoptedOrder()
                    .stream()
                    .map(order -> (NewOrderDTO) newOrderMapper.toDto(order))
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (NotFoundById notFoundById) {
            LOG.error("error" , notFoundById);
            return Collections.emptyList();
        }
    }

    @GetMapping("/custom/user")
    @CrossOrigin
    public Collection<InfoOrderDTO> getUserOrders() {
        try {
            LOG.debug("get User of Orders");
            Collection<Order> orders = orderService.getOrdersOfUser();
            return orders.stream()
                    .map(infoOrderMapper::toDto)
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (NotFoundById notFoundById) {
            notFoundById.printStackTrace();
            return Collections.emptyList();
        }
    }

    @GetMapping("/detail")
    @CrossOrigin
    public DetailOrderDTO getDetailOrder(@RequestParam(name = "id") Long id) throws NotFoundById {
        try {
            Order order = orderService.getOrder(id);
            InfoOrderDTO infoOrderDTO = infoOrderMapper.toDto(order);
            return new DetailOrderDTO(infoOrderDTO, order.getProgress());
        }
        catch (NotFoundById ex) {
            LOG.error("Error : not found by id", ex);
            return null;
        }
    }

    @GetMapping("/pesticides")
    @CrossOrigin
    public Collection<Pesticide> getPesticides(@RequestParam(name = "orderId") Long id) {
        LOG.debug("Get Pesticides");
        try {
            Order order = orderService.getOrder(id);
            return order.getPesticides();
        } catch (NotFoundById notFoundById) {
            LOG.error("Not found order by id : {}", id, notFoundById);
            notFoundById.printStackTrace();
            return null;
        }
    }

    @PostMapping("/change/process/area")
    @CrossOrigin
    public StatusResponse changeProccessArea(@RequestBody ChangeProcessAreaDTO changeProcessAreaDTO) {
        LOG.debug("change process area : {}", changeProcessAreaDTO);

        try {
            orderService.changeProcessArea(changeProcessAreaDTO);
            return StatusResponse.successResponse();
        } catch (NotFoundById | ExcessVolume ex) {
           LOG.error("err", ex);
           return StatusResponse.failureResponse();
        }
    }

    @PostMapping("/complete")
    @CrossOrigin
    public StatusResponse completeOrder(@RequestBody OrderIdDTO orderIdDTO) {
        LOG.debug("OrderIdDto : {}", orderIdDTO);
        try {
            orderService.completeOrder(orderIdDTO);
            return StatusResponse.successResponse();
        } catch (NotFoundById ex) {
            LOG.error("Error", ex);
            return StatusResponse.failureResponse();
        }
    }

    @PostMapping("/reorder")
    @CrossOrigin
    public StatusResponse reOrder(@RequestParam(name = "orderID")Long orderID ) {
        LOG.debug("Re-order");
        try {
            orderService.reOrder(orderID);
            return StatusResponse.successResponse();
        } catch (NotFoundById ex) {
            LOG.error( "Error", ex);
            return StatusResponse.failureResponse();
        }
    }
}