package org.mines.address.domain.service;

import org.mines.address.domain.model.Town;
import org.mines.address.port.driven.TownRepositoryPort;
import org.mines.address.port.driving.TownUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;


@Component
public class TownService implements TownUseCase {

    @Autowired
    private TownRepositoryPort townRepositoryPort;

    public TownService(TownRepositoryPort townRepositoryPort) {
        this.townRepositoryPort = townRepositoryPort;
    }

    @Override
    @Transactional
    public Town save(Town town) {
        if (town.name() == null || town.name().isEmpty()) {
            throw new IllegalArgumentException("Town name is required");
        }

        // This is a validation example: the rule should probably be relaxed
        // as multiple towns can have the same name
        if (townRepositoryPort
                .selectAll()
                .stream()
                .anyMatch(persisted -> persisted.name().equalsIgnoreCase(town.name()) && persisted.id() != town.id())
        ) {
            throw new IllegalArgumentException("Town name already exists");
        }

        if (town.id() == null) {
            return townRepositoryPort.insert(town);
        } else {
            return townRepositoryPort.update(town);
        }
    }

    @Override
    public Optional<Town> get(UUID uuid) {
        return townRepositoryPort.select(uuid);
    }

    @Override
    public Collection<Town> list() {
        return townRepositoryPort.selectAll();
    }

    @Override
    @Transactional
    public void remove(UUID uuid) {
        townRepositoryPort.delete(uuid);
    }
}
