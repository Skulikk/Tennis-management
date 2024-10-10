package com.tennis.tennis.dao.impl;

import com.tennis.tennis.dao.ReservationDAO;
import com.tennis.tennis.model.Reservation;
import com.tennis.tennis.model.Court;
import com.tennis.tennis.model.Customer;
import com.tennis.tennis.service.CustomerService;
import com.tennis.tennis.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationJdbcDAO implements ReservationDAO {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class ReservationRowMapper implements RowMapper<Reservation> {
        @Override
        public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            Long courtId = rs.getLong("court");
            Long customerId = rs.getLong("customer");
            LocalTime start = rs.getTime("start").toLocalTime();
            LocalTime ending = rs.getTime("ending").toLocalTime();
            LocalDate date = rs.getDate("date").toLocalDate();
            Boolean isDouble = rs.getBoolean("isDouble");

            Court court = new Court();
            court.setId(courtId);
            // Optionally you can fetch and set other details for the court from the Court table

            Customer customer = new Customer();
            customer.setId(customerId);
            // Optionally you can fetch and set other details for the customer from the Customer table

            return new Reservation(id, customer, court, start, ending, date, false, isDouble);
        }
    };

    private final String FIND_BY_ID_SQL =
            "select * from reservations where id = ?";

    @Override
    public Optional<Reservation> findById(Long id) {
        List<Reservation> reservations = jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new ReservationJdbcDAO.ReservationRowMapper());
        if (reservations.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(reservations.get(0));
        }
    }

    private final String FIND_ALL_SQL = "SELECT * FROM reservations";

    @Override
    public List<Reservation> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new ReservationJdbcDAO.ReservationRowMapper());
    }

    @Override
    public void save(Reservation reservation) {

        String sql = "INSERT INTO reservations (court, customer, start, ending, date, isDouble) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                reservation.getCourt().getId(),
                reservation.getCustomer().getId(),
                reservation.getStart(),
                reservation.getEnding(),
                reservation.getDate(),
                reservation.getIsDouble());
    }

    @Override
    public void update(Reservation reservation) {


        String sql = "UPDATE reservations SET court = ?, customer = ?, start = ?, ending = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                reservation.getCourt().getId(),
                reservation.getCustomer().getId(),
                reservation.getStart(),
                reservation.getEnding(),
                reservation.getDate(),
                reservation.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}