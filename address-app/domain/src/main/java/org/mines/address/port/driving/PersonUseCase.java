package org.mines.address.port.driving;

import org.mines.address.domain.model.Person;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface PersonUseCase {

    Person save(Person person);

    Optional<Person> get(UUID id);

    Collection<Person> findAll();

    boolean delete(UUID id);

    void createBirthCertificate(UUID personId, String birthPlace);
}
