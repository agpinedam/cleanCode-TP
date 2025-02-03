package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Town;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TownRowMapper implements RowMapper<Town> {
    @Override
    public Town mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Town.TownBuilder.aTown()
                .withId(UUID.fromString(rs.getString("id")))
                .withPostCode(rs.getInt("postCode"))
                .withName(rs.getString("name"))
                .build();
    }
}
