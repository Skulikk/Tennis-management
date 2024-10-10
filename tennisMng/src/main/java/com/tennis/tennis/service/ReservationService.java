package com.tennis.tennis.service;

import com.tennis.tennis.dao.ReservationDAO;
import com.tennis.tennis.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationDAO reservationDAO;

    public List<Reservation> findAllReservations() {
        return reservationDAO.findAll();
    }

    public Optional<Reservation> findReservationById(Long id) {
        return reservationDAO.findById(id);
    }

    public void createReservation(Reservation reservation) {
        reservationDAO.save(reservation);
    }

    public void updateReservation(Reservation reservation) {
        reservationDAO.update(reservation);
    }

    public void deleteReservation(long id) {
        reservationDAO.deleteById(id);
    }
}