package com.tennis.tennis.controller;

import com.tennis.tennis.model.Court;
import com.tennis.tennis.exception.BadRequestException;
import com.tennis.tennis.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/courts")
public class CourtController {

    @Autowired
    private CourtService courtService;

    @GetMapping
    public List<Court> getAllCourts() {
        return courtService.findAllCourts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Court> getCourtById(@PathVariable Long id) {
        Optional<Court> court = courtService.findCourtById(id);
        return court.map(ResponseEntity::ok)
                .orElseThrow(() -> new BadRequestException("Court not found with id " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourt(@RequestBody Map<String, Object> request) {
        Long surface = Long.valueOf(String.valueOf(request.get("surface")));
        courtService.saveCourt(surface);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCourt(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        Long surface = Long.valueOf(String.valueOf(request.get("surface")));
        courtService.updateCourt(id, surface);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        courtService.deleteCourt(id);
    }

}