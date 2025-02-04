package org.mines.address.domain.service;

import org.mines.address.domain.model.Person;
import org.mines.address.port.driven.PersonRepositoryPort;
import org.mines.address.port.driving.PersonUseCase;

import java.util.Optional;

public class PersonService {

    private final PersonRepositoryPort personRepository;
    private final PersonUseCase personUseCase; // Utilisation de PersonUseCase

    public PersonService(PersonRepositoryPort personRepository, PersonUseCase personUseCase) {
        this.personRepository = personRepository;
        this.personUseCase = personUseCase;
    }

    public Person save(Person person) {
        // Vérifier si la personne existe déjà via PersonUseCase
        Optional<Person> existingPerson = personUseCase.get(person.id());

        if (existingPerson.isPresent()) {
            throw new IllegalArgumentException("Person already exists with id: " + person.id());
        }

        // Insérer la personne dans le repository
        return personRepository.insert(person);
    }
}
