package org.mines.address.port.driven;

import org.mines.address.domain.model.Town;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TownRepositoryPort {

    Optional<Town> select(UUID uuid);

    Collection<Town> selectAll();

    Town insert(Town town);

    Town update(Town town);

    void delete(UUID uuid);
}
