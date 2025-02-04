package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Address;
import org.mines.address.domain.model.Town;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AddressRowMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Address.AddressBuilder.anAddress()
                .withId(UUID.fromString(rs.getString("id")))
                .withStreet(rs.getString("street"))
                .withNumber(rs.getInt("number"))
                .withTown(Town.TownBuilder.aTown()
                        .withId(UUID.fromString(rs.getString("town_id")))
                        .withName(rs.getString("town_name"))
                        .withPostCode(rs.getInt("town_postCode"))

                        .build())
                .build();
    }
}
