package com.tennis.tennis.dao;

import com.tennis.tennis.model.Customer;

import java.util.List;

public interface CustomerDAO {

    Customer findById(Long id);

    List<Customer> findAll();

    void save(Customer customer);
}