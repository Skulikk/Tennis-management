package com.tennis.tennis.dao.impl;

import com.tennis.tennis.model.Surface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SurfaceJdbcDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class SurfaceRowMapper implements RowMapper<Surface> {
        @Override
        public Surface mapRow(ResultSet rs, int rowNum) throws SQLException {
            Surface surface = new Surface();
            surface.setName(rs.getString("name"));
            surface.setPrice(rs.getInt("price"));
            return surface;
        }
    }

    public List<Surface> findAll() {
        String sql = "SELECT * FROM Surface";
        return jdbcTemplate.query(sql, new SurfaceRowMapper());
    }

    public Surface findById(Long id) {
        String sql = "SELECT * FROM Surface WHERE type = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new SurfaceRowMapper());
    }

    public int update(Surface surface) {
        String sql = "UPDATE Surface SET price = ? WHERE id = ?";
        return jdbcTemplate.update(sql, surface.getPrice(), surface.getId());
    }

}