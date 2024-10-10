package com.tennis.tennis.dao;

import com.tennis.tennis.model.Surface;

import java.util.List;

public interface SurfaceDAO {

    Surface findById(Long id);

    List<Surface> findAll();

    void update(Surface surface);

}