package org.address.infrastructure;

import org.address.infrastructure.config.PersistenceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Address;
import org.mines.address.domain.model.Town;
import org.mines.address.port.driven.AddressRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({SqlScriptsTestExecutionListener.class, TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = {PersistenceTestConfig.class})
@Sql(scripts = {"/data/clear.sql", "/data/town.sql"})
public class AddressRepositoryAdapterTest {

    @Autowired
    private AddressRepositoryPort addressRepository;

    @Test
    public void shouldInsertAnAddressWithAValidTown() {
        // GIVEN
        Address address = Address.AddressBuilder.anAddress()
                .withTown(Town.TownBuilder.aTown().withId(UUID.fromString("fb74d086-5a4a-11e7-907b-a6006ad3dba0")).build())
                .withNumber(666)
                .withStreet("street")
                .build();

        // WHEN
        Address persisted = addressRepository.insert(address);

        // THEN
        assertNotNull(persisted.id());
        assertEquals("street", persisted.street());
        assertEquals(666, persisted.number());
        assertEquals(UUID.fromString("fb74d086-5a4a-11e7-907b-a6006ad3dba0"), persisted.town().id());

        Collection<Address> addresses = addressRepository.selectAll();
        assertEquals(3, addresses.size());
        assertEquals("street", addresses.stream()
                .filter(entry -> entry.id().equals(persisted.id()))
                .findFirst()
                .map(Address::street).orElse(""));
    }

    @Test
    public void shouldInsertAnAddressWithAnUnknownTown() {
        // GIVEN
        Address address = Address.AddressBuilder.anAddress()
                .withTown(Town.TownBuilder.aTown().withId(UUID.randomUUID()).build())
                .withNumber(666)
                .withStreet("street")
                .build();

        // WHEN

        // THEN
        assertThrows(DataIntegrityViolationException.class, () -> {
            addressRepository.insert(address);
        });
    }

}
