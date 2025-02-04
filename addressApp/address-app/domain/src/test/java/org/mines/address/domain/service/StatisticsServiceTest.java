package org.mines.address.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Person;
import org.mines.address.domain.model.Statistics;
import org.mines.address.port.driven.StatisticsRepositoryPort;
import org.mines.address.port.driving.StatisticsUseCase;
import org.mines.address.domain.service.StatisticsService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatisticsServiceTest {
    @Mock
    private StatisticsRepositoryPort staticRepository;


    @Mock
    private PersonRepositoryPort personRepository;

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
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withDateOfBirth(LocalDate.of(2000, 1, 1)).build(),
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withDateOfBirth(LocalDate.of(2010, 5, 15)).build(),
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withDateOfBirth(LocalDate.of(1985, 7, 10)).build(),
                Person.PersonBuilder.aPerson().withId(UUID.randomUUID()).withDateOfBirth(LocalDate.of(1950, 3, 25)).build()
        );

        when(personRepository.findAll()).thenReturn(persons);

        // WHEN
        Statistics statistics = statisticsService.computeStatistics();

        // THEN
        assertEquals(4, statistics.totalPersons());
        assertEquals(0, statistics.totalBirthCertificates()); // Assuming no birth certificates are set
        assertEquals(34.5, statistics.averageAge(), 1.0); // Approximate
        assertEquals(Map.of("0-17", 1, "18-34", 1, "35-59", 1, "60+", 1), statistics.personsByAgeGroup());
    }

}