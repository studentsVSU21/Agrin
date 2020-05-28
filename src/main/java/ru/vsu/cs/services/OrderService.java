package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.OrderDTO;
import ru.vsu.cs.Entities.Customer;
import ru.vsu.cs.Entities.Order;
import ru.vsu.cs.Entities.Progress;
import ru.vsu.cs.Entities.Region;
import ru.vsu.cs.reposirories.OrderRepository;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

@Service
public class OrderService {
    private final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private CustomerService customerService;
    private OrderRepository orderRepository;
    private RegionService regionService;
    private ProgressService progressService;

    @Autowired
    public OrderService(
        CustomerService customerService,
        OrderRepository orderRepository,
        RegionService regionService,
        ProgressService progressService)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.regionService = regionService;
        this.progressService = progressService;
    }

    public void createOrder(OrderDTO orderDTO) throws NotFoundById
    {
        LOG.debug("Method create Order");
        Region region = regionService.findRegionByID(orderDTO.getRegionID());
        LOG.debug("Region : {}", region);
        Customer customer = customerService.addCustomer(orderDTO.getCustomer());
        LOG.debug("Customer : {}", customer);
        Order order = new Order();
        order.setArea(orderDTO.getArea());
        order.setCustomer(customer);
        order.setRegion(region);
        Progress progress = progressService.initProgress();
        order.setProgress(progress);
        LOG.debug("Order : {}", order);
        orderRepository.save(order);
        LOG.debug("Order : {}", order);
    }

    public Collection<Order> getNewOrder() throws NotFoundById {
        Collection<Order> orders = new LinkedList<>();
        for (Progress progress : progressService.getNewProgress()) {
            LOG.debug("progress : {}", progress);
            Optional<Order> optionalOrder = orderRepository.findOrderByProgress(progress);
            if (!optionalOrder.isPresent()){
                LOG.debug("Not Found order by progress");
                continue;
            }

            LOG.debug("check");
            orders.add(optionalOrder.get());
        }
        return orders;
     }

     public void rejectOrder(Long orderId) throws NotFoundById{
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (!optionalOrder.isPresent()) {
            throw new NotFoundById("order");
        }
        Order order = optionalOrder.get();
        Progress progress = order.getProgress();
        progressService.assumeStatusRejected(progress);
     }
}