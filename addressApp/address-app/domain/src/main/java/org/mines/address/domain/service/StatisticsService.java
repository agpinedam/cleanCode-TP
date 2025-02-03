package org.mines.address.domain.service;

import org.mines.address.domain.model.Statistics;
import org.mines.address.domain.repository.PersonRepository;
import org.mines.address.domain.repository.BirthCertificateRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsService {

    private final PersonRepository personRepository;
    private final BirthCertificateRepository birthCertificateRepository;

    public StatisticsService(PersonRepository personRepository, BirthCertificateRepository birthCertificateRepository) {
        this.personRepository = personRepository;
        this.birthCertificateRepository = birthCertificateRepository;
    }

    // Calculate the general statistics
    public Statistics calculateStatistics() {
        List<Person> persons = personRepository.findAll(); // Get all persons

        int totalPersons = persons.size();
        int totalBirthCertificates = birthCertificateRepository.count(); // Count birth certificates
        double averageAge = persons.stream()
                .mapToInt(person -> LocalDate.now().getYear() - person.birthDate().getYear())
                .average().orElse(0.0);

        Map<String, Integer> personsByAgeGroup = persons.stream()
                .collect(Collectors.groupingBy(this::getAgeGroup, Collectors.summingInt(person -> 1))); // Group by age

        return new Statistics(totalPersons, totalBirthCertificates, averageAge);
    }

    private String getAgeGroup(Person person) {
        int age = LocalDate.now().getYear() - person.birthDate().getYear();
        if (age < 18) return "under 18";
        if (age <= 35) return "18-35";
        if (age <= 50) return "36-50";
        return "50+";
    }
}
