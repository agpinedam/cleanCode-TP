package org.mines.address.domain.service;

import org.mines.address.domain.model.Person;
import org.mines.address.domain.model.Statistics;
import org.mines.address.port.driven.PersonRepositoryPort;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        Map<String, Integer> personsByAgeGroup = groupByAgeCategory(persons);

        return Statistics.StatisticsBuilder.aStatistics()
                .withTotalPersons(totalPersons)

                .withAverageAge(averageAge)
                .withPersonsByAgeGroup(personsByAgeGroup)
                .build();
    }

    private int calculateAge(Person person) {
        if (person.birthDate()== null) {
            return 0;
        }
        return Period.between(person.birthDate(), LocalDate.now()).getYears();
    }

    private Map<String, Integer> groupByAgeCategory(List<Person> persons) {
        return persons.stream()
                .collect(Collectors.groupingBy(
                        person -> getAgeCategory(calculateAge(person)),
                        Collectors.summingInt(p -> 1)
                ));
    }

    private String getAgeCategory(int age) {
        if (age < 18) return "0-17";
        if (age < 35) return "18-34";
        if (age < 60) return "35-59";
        return "60+";
    }
}