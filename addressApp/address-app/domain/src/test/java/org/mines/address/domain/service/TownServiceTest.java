package org.mines.address.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Town;
import org.mines.address.port.driven.TownRepositoryPort;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TownServiceTest {

    @Mock
    private TownRepositoryPort townRepositoryPort;

    @InjectMocks
    private TownService townService = new TownService(townRepositoryPort);

    @Test
    void shouldSaveATown() {
        // GIVEN
        Town town = Town.TownBuilder.aTown().withName("Saint genies des Mourgues").withPostCode(34160).build();

        UUID id = UUID.randomUUID();
        when(townRepositoryPort.insert(any())).thenReturn(
                Town.TownBuilder.aTown().withId(id).build()
        );

        // WHEN
        Town saved = townService.save(town);

        // THEN
       assertEquals(id, saved.id());
    }

    @Test
    void shouldNotSaveTownWithEmptyName() {
        // GIVEN
        Town town = Town.TownBuilder.aTown().withName("").withPostCode(34160).build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(town));
    }

    @Test
    void shouldNotSaveAnExistingTown() {
        // GIVEN

        UUID id = UUID.randomUUID();
        when(townRepositoryPort.selectAll()).thenReturn(List.of(Town.TownBuilder.aTown().withName("alreadyPresent").withId(id).build()));

        Town town = Town.TownBuilder.aTown().withName("alreadyPresent").build();

        // WHEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(town));
    }

    @Test
    void shouldRemoveATown() {
        // GIVEN
        UUID uuid = UUID.randomUUID();

        // WHEN
        townService.remove(uuid);

        // THEN
        verify(townRepositoryPort, times(1)).delete(uuid);
    }


}
