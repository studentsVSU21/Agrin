package ru.vsu.cs.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.cs.DTO.CustomerDTO;
import ru.vsu.cs.Entities.Customer;
import ru.vsu.cs.reposirories.CustomerRepository;

@Service
public class CustomerService {
    private Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(
            CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public Customer addCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFio(customerDTO.getFio());
        customer.setEmail(customerDTO.getMail());
        customer.setPhoneNumber(customerDTO.getPhone());
        customerRepository.save(customer);
        LOG.debug("customer : {}", customer);
        return customer;
    }

}
