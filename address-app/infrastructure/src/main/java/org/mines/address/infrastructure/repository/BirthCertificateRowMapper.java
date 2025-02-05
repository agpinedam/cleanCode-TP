package org.mines.address.infrastructure.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.mines.address.domain.model.BirthCertificate;

public class BirthCertificateRowMapper implements RowMapper<BirthCertificate> {
    @Override
    public BirthCertificate mapRow(ResultSet rs, int rowNum) throws SQLException {
        return BirthCertificate.BirthCertificateBuilder.aBirthCertificate()
                .withId(UUID.fromString(rs.getString("id")))
                .withPersonId(UUID.fromString(rs.getString("id")))
                .withBirthPlace(rs.getString("birth_place"))
                .build();
    }
    
}
