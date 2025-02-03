package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Person;
import org.mines.address.port.driven.PersonRepositoryPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PersonRepository implements PersonRepositoryPort {

    private final JdbcTemplate jdbcTemplate;

    public PersonRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person insert(Person person) {
        String sql = "INSERT INTO person (id, first_name, last_name, birth_date) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, person.id(), person.firstName(), person.lastName(), person.birthDate());
        return person;
    }

    @Override
    public Optional<Person> selectById(UUID id) {
        String sql = "SELECT * FROM person WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{id}, rs -> {
            if (rs.next()) {
                return Optional.of(mapRowToPerson(rs));
            }
            return Optional.empty();
        });
    }

    @Override
    public Collection<Person> selectAll() {
        String sql = "SELECT * FROM person";
        return Collections.singleton(jdbcTemplate.query(sql, this::mapRowToPerson));
    }

    @Override
    public void delete(Person person) {
        String sql = "DELETE FROM person WHERE id = ?";
        jdbcTemplate.update(sql, person.id());
    }

    private Person mapRowToPerson(ResultSet rs) throws SQLException {
        return new Person(
                UUID.fromString(rs.getString("id")),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("birth_date").toLocalDate()
        );
    }
}
