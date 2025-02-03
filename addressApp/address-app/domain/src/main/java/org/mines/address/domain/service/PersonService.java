package org.mines.address.domain.service;

import org.mines.address.domain.model.Person;
import org.mines.address.port.driven.PersonRepositoryPort;
import org.mines.address.port.driving.PersonUseCase;
import org.mines.address.port.driving.BirthCertificateUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Component
public class PersonService implements PersonUseCase {

    @Autowired
    private PersonRepositoryPort personRepositoryPort;

    @Autowired
    private BirthCertificateUseCase birthCertificateUseCase;

    private final Logger logger = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepositoryPort personRepositoryPort, BirthCertificateUseCase birthCertificateUseCase) {
        this.personRepositoryPort = personRepositoryPort;
        this.birthCertificateUseCase = birthCertificateUseCase;
    }

    @Override
    public Person save(Person person) {
        if (person.firstName().isBlank() || person.lastName().isBlank()) {
            throw new IllegalArgumentException("First and last name are required");
        }

        if (person.birthDate() == null) {
            throw new IllegalArgumentException("Birth date is required");
        }

        return personRepositoryPort.insert(person);
    }

    @Override
    public Optional<Person> get(UUID id) {
        return personRepositoryPort.selectById(id);
    }

    @Override
    public Collection<Person> findAll() {
        return personRepositoryPort.selectAll();
    }

    @Override
    public boolean delete(UUID id) {
        Optional<Person> person = personRepositoryPort.selectById(id);
        if (person.isPresent()) {
            personRepositoryPort.delete(person.get());
            return true;
        }
        return false;
    }

    // Optional: Create birth certificate
    @Override
    public void createBirthCertificate(UUID personId, String birthPlace) {
        birthCertificateUseCase.create(personId, birthPlace);
    }
}
