package com.tennis.tennis.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "court", referencedColumnName = "id")
    private Court court;

    @Column(name = "start", nullable = false)
    private LocalTime start;

    @Column(name = "ending", nullable = false)
    private LocalTime ending;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    private boolean deleted = Boolean.FALSE;

    @Column(name = "isDouble", nullable = false)
    private boolean isDouble;

    // Manually defined getter
    public boolean getIsDouble() {
        return isDouble;
    }
}