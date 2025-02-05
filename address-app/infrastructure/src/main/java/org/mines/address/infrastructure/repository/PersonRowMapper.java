package org.mines.address.infrastructure.repository;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.mines.address.domain.model.Person;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.PersonBuilder.aPerson()
                .withId(UUID.fromString(rs.getString("id")))
                .withFirstName(rs.getString("first_name"))
                .withLastName(rs.getString("last_name"))
                .withBirthDate(rs.getDate("birth_date").toLocalDate())
                .build();
    }
    
}
