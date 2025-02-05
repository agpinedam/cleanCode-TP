package org.mines.address.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;
import java.util.Objects;

import org.mines.address.domain.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.mines.address.port.driven.PersonRepositoryPort;

public class PersonRepositoryAdapter implements PersonRepositoryPort {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PersonRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    @Override
    public List<Person> findAll() {
        return this.jdbcTemplate.query("select id, first_name, last_name, birth_date from person", new PersonRowMapper());
    }

    @Override
    public Person insert(Person person) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("person");
        final UUID uuid = UUID.randomUUID();
        
        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("first_name", person.firstName())
                .addValue("last_name", person.lastName())
                .addValue("birth_date", person.birthDate());

        simpleJdbcInsert.execute(in);

        return Person.PersonBuilder.aPerson()
                .withId(uuid)
                .withFirstName(person.firstName())
                .withLastName(person.lastName())
                .withBirthDate(person.birthDate())
                .build();
    }

    @Override
    public Optional<Person> selectById(UUID id) {
        return this.jdbcTemplate.query("select id, first_name, last_name, birth_date from person where id = ?", new PersonRowMapper(), id).stream().findFirst();
    }

    public void delete(Person person) {
        this.jdbcTemplate.update("delete from person where id = ?", person.id());
    }
}
