package org.mines.address.domain.service;

import org.mines.address.domain.model.Address;
import org.mines.address.port.driven.AddressRepositoryPort;
import org.mines.address.port.driving.AddressUseCase;
import org.mines.address.port.driving.TownUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.UUID;

@Component
public class AddressService implements AddressUseCase {
    @Autowired
    private AddressRepositoryPort addressRepositoryPort;

    @Autowired
    private TownUseCase townUseCase;

    private final Logger logger = LoggerFactory.getLogger(AddressService.class);

    public AddressService(AddressRepositoryPort addressRepositoryPort, TownUseCase townUseCase) {
        this.addressRepositoryPort = addressRepositoryPort;
        this.townUseCase = townUseCase;
    }

    @Override
    public Address save(Address address) {
        if (address.town() == null
                || address.town().id() == null
                || townUseCase.get(address.town().id()).isEmpty()) {
            throw new IllegalArgumentException("Invalid town");
        } else if (address.street().isBlank()) {
            throw new IllegalArgumentException("street is required");
        } else if (address.number() <= 0) {
            throw new IllegalArgumentException("invalid number");
        }

        return addressRepositoryPort.insert(address);
    }

    @Override
    public Collection<Address> findByTown(UUID id) {
        return addressRepositoryPort.selectAll();
    }
}
