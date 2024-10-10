package com.tennis.tennis.service;

import com.tennis.tennis.dao.ReservationDAO;
import com.tennis.tennis.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurfaceService {

    @Autowired
    private ReservationDAO reservationDAO;

    public List<Reservation> getAllReservations() {
        return reservationDAO.findAll();
    }

    //public Reservation getReservationById(long id) {
    //    return reservationDAO.findById(id);
    //}

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