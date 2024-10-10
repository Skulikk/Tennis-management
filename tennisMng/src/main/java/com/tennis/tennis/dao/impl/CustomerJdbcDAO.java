package com.tennis.tennis.dao.impl;

import com.tennis.tennis.dao.CustomerDAO;
import com.tennis.tennis.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerJdbcDAO implements CustomerDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Customer> customerRowMapper = new RowMapper<Customer>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Customer(id, name);
        }
    };

    @Override
    public Customer findById(Long id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        List<Customer> customers = jdbcTemplate.query(sql, new Object[]{id}, customerRowMapper);
        return customers.isEmpty() ? null : customers.get(0);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customers";
        return jdbcTemplate.query(sql, customerRowMapper);
    }

    @Override
    public void save(Customer customer) {
        String sql = "INSERT INTO customers (id, name) VALUES (?, ?)";
        jdbcTemplate.update(sql, customer.getId(), customer.getName());
    }

}