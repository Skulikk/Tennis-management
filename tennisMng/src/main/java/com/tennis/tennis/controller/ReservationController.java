package com.tennis.tennis.controller;

import com.tennis.tennis.exception.BadRequestException;
import com.tennis.tennis.model.Court;
import com.tennis.tennis.model.Customer;
import com.tennis.tennis.service.CustomerService;
import com.tennis.tennis.service.ReservationService;
import com.tennis.tennis.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.findAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findReservationById(id);
        return reservation.map(ResponseEntity::ok)
                .orElseThrow(() -> new BadRequestException("Court not found with id " + id));
    }

    //aux method
    private Reservation mapToReservation(Map<String, Object> request) {
        Reservation reservation = new Reservation();

        Map<String, Object> customerMap = (Map<String, Object>) request.get("customer");
        Long customerId = ((Number) customerMap.get("id")).longValue();
        Customer customer = new Customer();
        customer.setId(customerId);

        Map<String, Object> courtMap = (Map<String, Object>) request.get("court");
        Long courtId = ((Number) courtMap.get("id")).longValue();
        Court court = new Court();
        court.setId(courtId);

        reservation.setCustomer(customer);
        reservation.setCourt(court);

        String start = (String) request.get("start");
        String ending = (String) request.get("ending");
        String date = (String) request.get("date");
        Boolean deleted = (Boolean) request.get("deleted");
        Boolean isDouble = (Boolean) request.get("isDouble");

        reservation.setStart(LocalTime.parse(start));
        reservation.setEnding(LocalTime.parse(ending));
        reservation.setDate(LocalDate.parse(date));
        reservation.setDeleted(deleted != null ? deleted : Boolean.FALSE);
        reservation.setDouble(isDouble != null ? isDouble : Boolean.FALSE);

        return reservation;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@RequestBody Map<String, Object> request) {

        Reservation reservation = mapToReservation(request);
        if (customerService.getCustomerById(reservation.getCustomer().getId()) == null) {
            //customer doesnt exist -> insert new
            String name = (String) request.get("name");
            Map<String, Object> customerMap = (Map<String, Object>) request.get("customer");
            Long customerId = ((Number) customerMap.get("id")).longValue();
            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setName(name);
            customerService.createCustomer(customer);
        }
        reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateReservation(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        //reservationService.updateReservation(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}