package org.mines.address.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mines.address.domain.model.BirthCertificate;
import org.mines.address.port.driven.BirthCertificateRepositoryPort;
import org.mines.address.port.driving.BirthCertificateUseCase;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BirthCertificateTest {
    @Mock
    private BirthCertificateRepositoryPort birthCertificateRepository;;

    @Mock 
    private BirthCertificateUseCase birthCertificateUseCase;

    @InjectMocks
    private BirthCertificateService birthCertificateService;

    @Test
    void shouldSaveABirthCertificate() {
        // GIVEN
        UUID birthCertificateId = UUID.randomUUID();
        BirthCertificate birthCertificate = BirthCertificate.BirthCertificateBuilder.aBirthCertificate()
                .withId(birthCertificateId)
                .build();

        when(birthCertificateRepository.insert(any())).thenReturn(birthCertificate);

        // WHEN
        BirthCertificate savedBirthCertificate = birthCertificateService.save(BirthCertificate.BirthCertificateBuilder.aBirthCertificate()
                .withId(birthCertificateId)
                .withPersonId(UUID.randomUUID())
                .withBirthPlace("Paris")
                .build());

        // THEN
        assertEquals(birthCertificate.id(), savedBirthCertificate.id());
    }

    @Test
    void shouldThrowExceptionWhenBirthCertificateAlreadyExists() {
        // GIVEN
        UUID birthCertificateId = UUID.randomUUID();
        BirthCertificate birthCertificate = BirthCertificate.BirthCertificateBuilder.aBirthCertificate()
                .withId(birthCertificateId)
                .withPersonId(UUID.randomUUID())
                .withBirthPlace("Paris")
                .build();

        when(birthCertificateUseCase.get(birthCertificate.id())).thenReturn(Optional.of(birthCertificate));

        // WHEN
        BirthCertificate savedBirthCertificate = birthCertificateService.save(BirthCertificate.BirthCertificateBuilder.aBirthCertificate()
                .withId(birthCertificateId)
                .withPersonId(UUID.randomUUID())
                .withBirthPlace("Paris")
                .build());

        // THEN
        assertEquals(birthCertificate.id(), savedBirthCertificate.id());
    }
    
}
