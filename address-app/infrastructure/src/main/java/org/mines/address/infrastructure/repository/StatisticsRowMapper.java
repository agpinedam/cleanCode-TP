package org.mines.address.infrastructure.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mines.address.domain.model.Statistics;

public class StatisticsRowMapper implements RowMapper<Statistics> {

    @Override
    public Statistics mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Statistics.StatisticsBuilder.aStatistics()
                .withTotalPersons(rs.getInt("total_persons"))
                .withTotalBirthCertificates(rs.getInt("total_birth_certificates"))
                .withAverageAge(rs.getDouble("average_age"))
                .build();
    }
    

}
