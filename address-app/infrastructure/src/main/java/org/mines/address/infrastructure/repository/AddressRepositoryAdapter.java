package org.mines.address.infrastructure.repository;

import org.mines.address.domain.model.Address;
import org.mines.address.port.driven.AddressRepositoryPort;
import org.mines.address.port.driven.TownRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Repository
public class AddressRepositoryAdapter implements AddressRepositoryPort {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private TownRepositoryPort townRepositoryPort;

    public AddressRepositoryAdapter(JdbcTemplate jdbcTemplate, TownRepositoryPort townRepositoryPort) {
        this.jdbcTemplate = jdbcTemplate;
        this.townRepositoryPort = townRepositoryPort;
    }

    @Override
    public Address insert(Address address) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(Objects.requireNonNull(jdbcTemplate.getDataSource())).withTableName("address");
        final UUID uuid = UUID.randomUUID();

        final SqlParameterSource in = new MapSqlParameterSource()
                .addValue("id", uuid)
                .addValue("town_id", address.town().id())
                .addValue("number", address.number())
                .addValue("street", address.street());

        simpleJdbcInsert.execute(in);

        return Address.AddressBuilder.anAddress()
                .withId(uuid)
                .withStreet(address.street())
                .withNumber(address.number())
                .withTown(address.town())
                .build();
    }

    @Override
    public Collection<Address> selectAll() {
        String sql = "select a.id,a.number,a.street,t.id town_id,t.name town_name,t.postCode town_postCode from address a inner join town t on a.town_id=t.id";
        return jdbcTemplate.query(sql, new AddressRowMapper());
    }
}
