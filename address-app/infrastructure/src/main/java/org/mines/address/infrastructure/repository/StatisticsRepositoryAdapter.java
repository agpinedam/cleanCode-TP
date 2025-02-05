package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Statistics;
import org.mines.address.port.driven.StatisticsRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticsRepositoryAdapter implements StatisticsRepositoryPort {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public StatisticsRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Statistics getStatistics() {
        String sql = "SELECT " +
                "(SELECT COUNT(*) FROM person) AS total_persons, " +
                "(SELECT COUNT(*) FROM birth_certificate) AS total_birth_certificates, " +
                "(SELECT AVG(EXTRACT(YEAR FROM AGE(birth_date))) FROM person) AS average_age " +
                "FROM person LIMIT 1;";

        return jdbcTemplate.queryForObject(sql, new StatisticsRowMapper());
    }
}