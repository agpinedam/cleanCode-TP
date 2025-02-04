package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Town;
import org.mines.address.port.driven.TownRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Repository
public class TownRepositoryAdapter implements TownRepositoryPort {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TownRepositoryAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Town> select(UUID uuid) {
        return this.jdbcTemplate.query("select id,postCode,name from town where id=?", new TownRowMapper(), uuid).stream().findFirst();
    }

    @Override
    public Collection<Town> selectAll() {
        return jdbcTemplate.query("select id,postCode,name from town", new TownRowMapper());
    }

    @Override
    public Town insert(Town town) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("town");
        final UUID uuid = UUID.randomUUID();

        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("postCode", town.postCode())
                .addValue("name", town.name());

        simpleJdbcInsert.execute(in);

        return Town.TownBuilder.aTown()
                .withId(uuid)
                .withPostCode(town.postCode())
                .withName(town.name())
                .build();

    }

    @Override
    public Town update(Town town) {
        jdbcTemplate.update("update town set name = ?, postCode = ? where id = ?", town.name(), town.postCode(), town.id());

        return town;
    }

    @Override
    public void delete(UUID uuid) {
        jdbcTemplate.update("delete from address where town_id=?", uuid);
        jdbcTemplate.update("delete from town where id=?", uuid);
    }
}
