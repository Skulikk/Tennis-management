package com.tennis.tennis.dao;

import com.tennis.tennis.model.Court;

import java.util.List;
import java.util.Optional;

public interface CourtDAO {

    Optional<Court> findById(Long id);

    List<Court> findAll();

    void saveNew(long surface);

    void edit(long id, long surface);

    void delete(long id);

}