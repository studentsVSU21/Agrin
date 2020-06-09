package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.CustomExceptions.ExcessVolume;
import ru.vsu.cs.CustomExceptions.NotFoundById;
import ru.vsu.cs.DTO.ChangeProcessAreaDTO;
import ru.vsu.cs.DTO.NotConfirmedOrderDTO;
import ru.vsu.cs.DTO.OrderDTO;
import ru.vsu.cs.DTO.OrderIdDTO;
import ru.vsu.cs.Entities.*;
import ru.vsu.cs.reposirories.OrderRepository;

import java.time.LocalDate;
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
    private AuthDataService authDataService;
    private UserService userService;
    private PesticideService pesticideService;

    @Autowired
    public OrderService(
        CustomerService customerService,
        OrderRepository orderRepository,
        RegionService regionService,
        ProgressService progressService,
        AuthDataService authDataService,
        UserService userService,
        PesticideService pesticideService)
    {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.regionService = regionService;
        this.progressService = progressService;
        this.authDataService = authDataService;
        this.userService = userService;
        this.pesticideService = pesticideService;
    }

    public Order createOrder(OrderDTO orderDTO) throws NotFoundById
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
        progress.setDateStart(orderDTO.getDate());
        order.setProgress(progress);
        LOG.debug("Order : {}", order);
        orderRepository.save(order);
        LOG.debug("Order : {}", order);
        return order;
    }

    public void createUserOrder(OrderDTO orderDTO) throws NotFoundById {
        Optional<User> optionalUser = authDataService.getUserFromUserDetails();
        if (!optionalUser.isPresent()) {
            LOG.error("Not auth user");
            return;
        }
        Order order = createOrder(orderDTO);
        userService.addOrderToUser(optionalUser.get(), order);
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


    /**
     * Метод, который позволяет достать заказ по идентификатору,
     * если такого нет возбуждает исключение
     * @param id
     * @return
     * @throws NotFoundById
     */
     public Order getOrder(Long id) throws NotFoundById {
         Optional<Order> optionalOrder = orderRepository.findById(id);
         if (!optionalOrder.isPresent()) {
             throw new NotFoundById("order");
         }
         return optionalOrder.get();
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

    /**
     * Метод возвращает все заказы пользователей
     * @return
     * @throws NotFoundById
     */
    public Collection<Order> getOrdersOfUser() throws NotFoundById {
        Optional<User> optionalUser = authDataService.getUserFromUserDetails();
        if (!optionalUser.isPresent()) {
            throw new NotFoundById("user");
        }
        User user = optionalUser.get();
        LOG.debug("User : {}", user);
        return user.getOrders();
    }

    public void confirmOrder(NotConfirmedOrderDTO notConfirmedOrderDTO) throws NotFoundById {
        Order order = getOrder(notConfirmedOrderDTO.getOrderId());
        LOG.debug("Confirm order : {}", order);
        Pesticide pesticide = pesticideService.getPesticideById(notConfirmedOrderDTO.getPesticideId());
        LOG.debug("Pesticide : {}", pesticide);
        order.getProgress().setDateStart(notConfirmedOrderDTO.getStartDate());
        order.getProgress().setDateEnd(notConfirmedOrderDTO.getEndDate());
        order.getPesticides().add(pesticide);
        progressService.assumeStatusAdopted(order.getProgress());
        orderRepository.save(order);
        LOG.debug("Completed confirmation : {}", order );
    }

    public Collection<Order> getAdoptedOrder() throws NotFoundById {
        Collection<Order> orders = new LinkedList<>();
        for ( Progress progress : progressService.getApodtedProgress()) {
            Optional<Order> optionalOrder = orderRepository.findOrderByProgress(progress);
            if (!optionalOrder.isPresent()){
                LOG.debug("Not Found order by progress");
                continue;
            }
            orders.add(optionalOrder.get());
        }
        return orders;
    }

    public void changeProcessArea(ChangeProcessAreaDTO changeProcessAreaDTO) throws NotFoundById, ExcessVolume {
        Order order = getOrder(changeProcessAreaDTO.getOrderID());
        Progress progress = order.getProgress();
        if ( order.getArea() - progress.getProcessedArea() >= changeProcessAreaDTO.getProcessArea() ) {
            progress.setProcessedArea(progress.getProcessedArea() + changeProcessAreaDTO.getProcessArea());
            orderRepository.save(order);
        }
        else {
            throw new ExcessVolume();
        }
    }

    public void completeOrder(OrderIdDTO orderIdDTO) throws NotFoundById {
        LOG.debug("complete order");
        Order order = getOrder(orderIdDTO.getOrderId());
        progressService.progressAssumeStatusComplete(order.getProgress());
        orderRepository.save(order);
    }

    public void reOrder(Long orderId) throws NotFoundById {
        Optional<User> optionalUser = authDataService.getUserFromUserDetails();
        if (!optionalUser.isPresent()) {
            LOG.error("Not auth user");
            return;
        }
        LOG.debug("Service Re-order");
        Order oldOrder = getOrder(orderId);
        Order order = new Order();
        order.setArea(oldOrder.getArea());
        order.setCustomer(oldOrder.getCustomer());
        order.setRegion(oldOrder.getRegion());
        Progress progress = progressService.initProgress();
        progress.setDateStart(LocalDate.now());
        order.setProgress(progress);
        LOG.debug("Order : {}", order);
        orderRepository.save(order);
        LOG.debug("Order : {}", order);
        userService.addOrderToUser(optionalUser.get(), order);
    }
}