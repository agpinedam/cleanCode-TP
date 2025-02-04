package org.mines.address.port.driven;

import org.mines.address.domain.model.Person;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepositoryPort {

    Person insert(Person person);

    Optional<Person> selectById(UUID id);

    Collection<Person> selectAll();

    void delete(Person person);

    List<Person> findAll();
}
