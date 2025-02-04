package org.mines.address.port.driven;

import org.mines.address.domain.model.Address;

import java.util.Collection;

public interface AddressRepositoryPort {

    Address insert(Address address);

    Collection<Address> selectAll();
}
