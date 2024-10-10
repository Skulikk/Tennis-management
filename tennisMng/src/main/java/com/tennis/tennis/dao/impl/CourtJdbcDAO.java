package com.tennis.tennis.dao.impl;

import com.tennis.tennis.dao.CourtDAO;
import com.tennis.tennis.model.Court;
import com.tennis.tennis.model.Surface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class CourtJdbcDAO implements CourtDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class CourtRowMapper implements RowMapper<Court> {
        @Override
        public Court mapRow(ResultSet rs, int rowNum) throws SQLException {
            Court court = new Court();
            court.setId(rs.getLong("court_id"));

            Surface surface = new Surface();
            surface.setId(rs.getLong("surface_id"));
            surface.setName(rs.getString("surface_name"));
            surface.setPrice(rs.getInt("surface_price"));

            court.setSurface(surface);
            return court;
        }
    }

    private final String FIND_ALL_SQL =
            "SELECT c.id AS court_id, s.id AS surface_id, s.name AS surface_name, s.price AS surface_price FROM courts c INNER JOIN surfaces s ON c.surface = s.id where c.deleted = false";

    private final String FIND_BY_ID_SQL =
            "SELECT c.id AS court_id, s.id AS surface_id, s.name AS surface_name, s.price AS surface_price FROM courts c INNER JOIN surfaces s ON c.surface = s.id WHERE c.id = ? where c.deleted = false";
    // Method to fetch all courts along with their surfaces
    @Override
    public List<Court> findAll() {
        return jdbcTemplate.query(FIND_ALL_SQL, new CourtRowMapper());
    }

    @Override
    public Optional<Court> findById(Long id) {
        List<Court> courts = jdbcTemplate.query(FIND_BY_ID_SQL, new Object[]{id}, new CourtRowMapper());
        if (courts.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(courts.get(0));
        }
    }

    @Override
    public void saveNew(long id) {
        String sql = "INSERT INTO courts (surface, deleted) VALUES (?, false)";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void edit(long id, long surface) {
        String sql = "update courts set surface = ? where id = ?";
        jdbcTemplate.update(sql, surface, id);
    }

    @Override
    public void delete(long id) {
        String sqlPre = "update reservations set deleted = true where court = ?";
        jdbcTemplate.update(sqlPre, id);

        String sql = "update courts set deleted = true where id = ?";
        jdbcTemplate.update(sql, id);
    }

}