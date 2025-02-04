package org.mines.address.port.driven;

import org.mines.address.domain.model.BirthCertificate;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public interface BirthCertificateRepositoryPort {

    BirthCertificate insert(BirthCertificate birthCertificate);

    Optional<BirthCertificate> selectByPersonId(UUID personId);

    int count();

    Collection<BirthCertificate> selectAll();
}
