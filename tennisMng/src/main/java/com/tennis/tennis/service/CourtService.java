package com.tennis.tennis.service;

import com.tennis.tennis.dao.CourtDAO;
import com.tennis.tennis.model.Court;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourtService {

    @Autowired
    private CourtDAO courtDAO;

    public List<Court> findAllCourts() {
        return courtDAO.findAll();
    }

    public Optional<Court> findCourtById(Long id) {
        return courtDAO.findById(id);
    }

    public void saveCourt(Long surface) {
        courtDAO.saveNew(surface);
    }

    public void updateCourt(Long id, Long surface) {
        courtDAO.edit(id, surface);
    }

    public void deleteCourt(Long id) {
        courtDAO.delete(id);
    }

}