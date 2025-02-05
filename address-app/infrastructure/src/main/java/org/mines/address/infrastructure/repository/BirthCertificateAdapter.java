package org.mines.address.infrastructure.repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.mines.address.domain.model.BirthCertificate;
import org.mines.address.port.driven.BirthCertificateRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;


public class BirthCertificateAdapter implements BirthCertificateRepositoryPort{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BirthCertificateAdapter(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BirthCertificate insert(BirthCertificate birthCertificate){
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("birth_certificates");
        final UUID uuid = UUID.randomUUID();

        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("person_id", birthCertificate.personId())
                .addValue("birth_place", birthCertificate.birthPlace());

        simpleJdbcInsert.execute(in);

        return BirthCertificate.BirthCertificateBuilder.aBirthCertificate()
                .withId(uuid)
                .withPersonId(birthCertificate.personId())
                .withBirthPlace(birthCertificate.birthPlace())
                .build();
    }

    @Override
    public Optional<BirthCertificate> selectByPersonId(UUID personId){
        return this.jdbcTemplate.query("select id, person_id, birth_place from birth_certificates where person_id = ?", new BirthCertificateRowMapper(), personId).stream().findFirst();
    }

    @Override
    public int count(){
        return this.jdbcTemplate.query("select count(*) from birth_certificates", new BirthCertificateRowMapper()).size();
    }

    @Override
    public Collection<BirthCertificate> selectAll(){
        String sql = "select id, person_id, birth_place from birth_certificates";
        return jdbcTemplate.query(sql, new BirthCertificateRowMapper());
    }
    
}
