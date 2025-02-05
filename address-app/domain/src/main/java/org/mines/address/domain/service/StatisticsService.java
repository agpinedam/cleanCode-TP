package org.mines.address.domain.service;

import org.mines.address.domain.model.Person;
import org.mines.address.domain.model.Statistics;
import org.mines.address.port.driven.PersonRepositoryPort;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class StatisticsService {

    private final PersonRepositoryPort personRepository;

    public StatisticsService(PersonRepositoryPort personRepository) {
        this.personRepository = personRepository;
    }

    public Statistics computeStatistics() {
        List<Person> persons = personRepository.findAll();

        int totalPersons = persons.size();


        double averageAge = persons.stream()
                .mapToInt(this::calculateAge)
                .average()
                .orElse(0.0);

        return Statistics.StatisticsBuilder.aStatistics()
                .withTotalPersons(totalPersons)

                .withAverageAge(averageAge)
                .build();
    }

    private int calculateAge(Person person) {
        if (person.birthDate()== null) {
            return 0;
        }
        return Period.between(person.birthDate(), LocalDate.now()).getYears();
    }

}