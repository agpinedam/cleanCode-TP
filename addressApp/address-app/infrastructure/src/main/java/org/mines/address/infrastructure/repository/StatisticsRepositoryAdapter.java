package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Statistics;
import org.mines.address.port.driven.StatisticsRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    private static class StatisticsRowMapper implements RowMapper<Statistics> {
        @Override
        public Statistics mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Statistics(
                    rs.getInt("total_persons"),
                    rs.getInt("total_birth_certificates"),
                    rs.getDouble("average_age")
            );
        }
    }
}
