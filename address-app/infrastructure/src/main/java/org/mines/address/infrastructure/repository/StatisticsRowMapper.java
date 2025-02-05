package org.mines.address.infrastructure.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mines.address.domain.model.Statistics;

public class StatisticsRowMapper implements RowMapper<Statistics> {

    @Override
    public Statistics mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Statistics.StatisticsBuilder.aStatistics()
                .withTotalPersons(rowNum)
                .withTotalBirthCertificates(rowNum)
                .withAverageAge(rowNum)
                .withPersonsByAgeGroup(null)
                .build();
    }

}
