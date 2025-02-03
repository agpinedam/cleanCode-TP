package org.mines.address.domain.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mines.address.domain.model.Person;
import org.mines.address.port.driven.PersonRepositoryPort;
import org.mines.address.port.driving.BirthCertificateUseCase;
import org.mines.address.port.driving.PersonUseCase;
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
    private PersonRepositoryPort personRepository;

    @Mock
    private PersonUseCase personUseCase ;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldSaveAPerson() {
        // GIVEN
        UUID personId = UUID.randomUUID();
        Person person = Person.PersonBuilder.aPerson()
                .withId(personId)
                .build();

        when(personRepository.insert(any())).thenReturn(person);

        // WHEN
        Person savedPerson = personService.save(Person.PersonBuilder.aPerson()
                .withId(personId)
                .withFirstName("toto")
                .withLastName("tati")
                .build());

        // THEN
        assertEquals(person.id(), savedPerson.id());
    }


    @Test
    void shouldThrowExceptionWhenPersonAlreadyExists() {
        // GIVEN
        UUID personId = UUID.randomUUID();
        Person person = Person.PersonBuilder.aPerson()
                .withId(personId)
                .withFirstName("John")
                .withLastName("Doe")
                .build();

        // Mock behavior: The person already exists
        when(personUseCase.get(personId)).thenReturn(Optional.of(person));

        // WHEN & THEN: Expect an exception when trying to save
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            personService.save(person);
        });

        assertEquals("Person already exists with id: " + personId, exception.getMessage());

        // Verify that the repository insert was never called
        verifyNoInteractions(personRepository);
    }

    }

