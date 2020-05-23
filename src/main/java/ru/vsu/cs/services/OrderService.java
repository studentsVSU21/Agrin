package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    public void createOrder() {
        LOG.debug("Method create Order");
    }
}
