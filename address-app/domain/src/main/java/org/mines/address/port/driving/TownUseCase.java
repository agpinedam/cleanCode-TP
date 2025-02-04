package org.mines.address.port.driving;

import org.mines.address.domain.model.Town;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface TownUseCase {

    Town save(Town town);

    Optional<Town> get(UUID uuid);

    Collection<Town> list();

    void remove(UUID uuid);
}
