package org.address.infrastructure;


import org.address.infrastructure.config.PersistenceTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Town;
import org.mines.address.port.driven.TownRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners({SqlScriptsTestExecutionListener.class, TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(classes = {PersistenceTestConfig.class})
@Sql(scripts = {"/data/clear.sql", "/data/town.sql"})
class TownRepositoryAdapterTest {

    @Autowired
    private TownRepositoryPort townRepository;

//    @BeforeEach
//    @Transactional
//    @Rollback(false)
//    public void fixtureData() {
//        Town town = Town.TownBuilder.aTown().withName("name").build();
//        townRepository.insert(town);
//    }

    @Test
    public void shouldFindAll() {
        Collection<Town> all = townRepository.selectAll();

        assertEquals(1, all.size());
    }

    @Test
    void shouldDeleteATownAndAllItsAddresses() {
        townRepository.delete(UUID.fromString("fb74d086-5a4a-11e7-907b-a6006ad3dba0"));
    }

}