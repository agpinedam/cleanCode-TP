package org.mines.address.port.driving;

import org.mines.address.domain.model.Address;

import java.util.Collection;
import java.util.UUID;

public interface AddressUseCase {

    Address save(Address address);

    Collection<Address> findByTown(UUID id);
}
