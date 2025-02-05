package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class PersonRowMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Person.PersonBuilder.aPerson()
                .withId(UUID.fromString(rs.getString("id")))
                .withFirstName(rs.getString("first_name"))
                .withLastName(rs.getString("last_name"))
                .withBirthDate(rs.getObject("date_of_birth", LocalDate.class))
                .build();
    }
}
