package org.mines.address.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Person;
import org.mines.address.domain.model.Statistics;
import org.mines.address.port.driven.PersonRepositoryPort;
import org.mines.address.port.driven.StatisticsRepositoryPort;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {

    @Mock
    private StatisticsRepositoryPort statisticsRepository;

    @Mock
    private PersonRepositoryPort personRepository;

    @InjectMocks
    private StatisticsService statisticsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        statisticsService = new StatisticsService(personRepository);
    }

    @Test
    void shouldComputeStatisticsCorrectly() {
        // GIVEN
        List<Person> persons = List.of(
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withBirthDate(LocalDate.of(2000, 1, 1)).build(),
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withBirthDate(LocalDate.of(2010, 5, 15)).build(),
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withBirthDate(LocalDate.of(1985, 7, 10)).build(),
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withBirthDate(LocalDate.of(1950, 3, 25)).build()
        );

        when(personRepository.findAll()).thenReturn(persons);

        // WHEN
        Statistics statistics = statisticsService.computeStatistics();

        // THEN
        assertEquals(4, statistics.totalPersons());
        assertEquals(0, statistics.totalBirthCertificates()); // Assuming no birth certificates are set
        assertEquals(38.5, statistics.averageAge(), 1.0); // Approximate
    }
}
