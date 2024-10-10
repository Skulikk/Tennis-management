package com.tennis.tennis.service;

import com.tennis.tennis.dao.CustomerDAO;
import com.tennis.tennis.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public List<Customer> getAllCustomers() {
        return customerDAO.findAll();
    }

    public Customer getCustomerById(long id) {
        return customerDAO.findById(id);
    }

    public void createCustomer(Customer customer) {
        customerDAO.save(customer);
    }

}