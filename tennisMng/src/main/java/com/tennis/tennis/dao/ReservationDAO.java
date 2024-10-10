package com.tennis.tennis.dao;

import com.tennis.tennis.model.Reservation;

import java.util.List;
import java.util.Optional;


public interface ReservationDAO {

    Optional<Reservation> findById(Long id);

    List<Reservation> findAll();

    void save(Reservation reservation);

    void update(Reservation reservation);

    void deleteById(Long id);
}