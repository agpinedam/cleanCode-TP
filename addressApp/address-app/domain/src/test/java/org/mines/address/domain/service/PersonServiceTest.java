package org.mines.address.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

        @Mock
        private  ;

        @Mock
        private TownUseCase townUseCase;

        @InjectMocks
        private AddressService addressService = new AddressService(addressRepositoryPort, townUseCase);

        @Test
        void shouldSaveAnAddress() {
            // GIVEN
            UUID townId = UUID.randomUUID();
            Town town = Town.TownBuilder.aTown().withId(townId).build();
            when(townUseCase.get(townId)).thenReturn(Optional.of(town));

            Address persisted = Address.AddressBuilder.anAddress()
                    .withId(UUID.randomUUID())
                    .withTown(town)
                    .build();
            when(addressRepositoryPort.insert(any())).thenReturn(persisted);

            // WHEN
            Address address = addressService.save(Address.AddressBuilder.anAddress()
                    .withTown(town)
                    .withNumber(10)
                    .withStreet("Rue de l'avenue")
                    .build());

            // THEN
            assertEquals(persisted.id(), address.id());
        }

        @Test
        void shouldNotSaveAddressWhenTownDoesNotExist() {
            // GIVEN
            when(townUseCase.get(any())).thenReturn(Optional.empty());

            // WHEN
            // THEN

            IllegalArgumentException invalidTown = assertThrows(IllegalArgumentException.class, () -> addressService.save(Address.AddressBuilder.anAddress()
                    .withTown(Town.TownBuilder.aTown().withId(UUID.randomUUID()).build())
                    .withNumber(10)
                    .withStreet("Rue de l'avenue")
                    .build()));

            assertEquals("Invalid town", invalidTown.getMessage());

            verifyNoInteractions(addressRepositoryPort);
        }

    }

